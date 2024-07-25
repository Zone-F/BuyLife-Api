-- 创建数据库 BuyLife
CREATE DATABASE IF NOT EXISTS BuyLife;
USE BuyLife;

-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识每个用户的自增主键',
    username VARCHAR(255) NOT NULL COMMENT '用户的昵称或姓名，用于登录和展示',
    email VARCHAR(255) NOT NULL COMMENT '用户的邮箱地址，用于注册和通知',
    password VARCHAR(255) NOT NULL COMMENT '用户密码，需经过加密处理',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳'
) ENGINE=InnoDB COMMENT='用户信息表';

-- 创建打卡任务表
CREATE TABLE IF NOT EXISTS check_in_tasks (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识每个任务的自增主键',
    user_id Bigint NOT NULL COMMENT '与用户表相关联的外键，标识创建任务的用户',
    title VARCHAR(255) NOT NULL COMMENT '用户为任务定义的标题',
    description TEXT COMMENT '任务的详细描述',
    point INT NOT NULL COMMENT '任务积分',
    status INT NOT NULL DEFAULT 1 COMMENT '任务状态：1活跃，0关闭',
    start_date DATE NOT NULL COMMENT '任务开始执行的日期',
    repeat_cycle ENUM('daily', 'weekly', 'monthly', 'yearly') NOT NULL COMMENT '任务重复的周期类型',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='打卡任务表';

-- 创建打卡记录表
CREATE TABLE IF NOT EXISTS check_ins (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '打卡记录的唯一标识',
    task_id Bigint NOT NULL COMMENT '关联打卡任务表的外键',
    user_id Bigint NOT NULL COMMENT '关联用户表的外键',
    check_in_time DATE NOT NULL COMMENT '用户进行打卡的日期',
    status INT NOT NULL DEFAULT 0 COMMENT '0：未打卡，1：已打卡',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳',
    expiry_time TIMESTAMP NOT NULL COMMENT '打卡任务的过期时间',
    FOREIGN KEY (task_id) REFERENCES check_in_tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='打卡记录表';

-- 创建积分记录表
CREATE TABLE IF NOT EXISTS point_records (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '积分记录的唯一标识',
    user_id Bigint NOT NULL COMMENT '关联用户表的外键',
    change_points INT NOT NULL COMMENT '积分的增加或减少数',
    current_points INT NOT NULL COMMENT '变动后用户的总积分',
    change_reason VARCHAR(255) NOT NULL COMMENT '积分变动的原因描述',
    change_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '积分变动的时间戳',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='积分记录表';

-- 创建商品表
CREATE TABLE IF NOT EXISTS items (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
    user_id Bigint COMMENT '用户ID',
    name VARCHAR(255) NOT NULL COMMENT '商品名称',
    description TEXT COMMENT '商品描述',
    price INT NOT NULL COMMENT '积分价格',
    stock INT NOT NULL COMMENT '库存数量',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB COMMENT='商品表';

-- 创建用户购买历史表
CREATE TABLE IF NOT EXISTS purchase_history (
    id Bigint AUTO_INCREMENT PRIMARY KEY COMMENT '购买记录ID',
    user_id Bigint NOT NULL COMMENT '用户ID',
    item_id Bigint NOT NULL COMMENT '商品ID',
    quantity INT NOT NULL COMMENT '购买数量',
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '购买时间',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建的时间戳',
    update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='用户购买历史表';