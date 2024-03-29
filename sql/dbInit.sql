-- 创建数据库 BuyLife
CREATE DATABASE IF NOT EXISTS BuyLife;
USE BuyLife;

-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识每个用户的自增主键',
    username VARCHAR(255) NOT NULL COMMENT '用户的昵称或姓名，用于登录和展示',
    email VARCHAR(255) NOT NULL COMMENT '用户的邮箱地址，用于注册和通知',
    password VARCHAR(255) NOT NULL COMMENT '用户密码，需经过加密处理',
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册的时间戳'
) ENGINE=InnoDB;

-- 创建打卡任务表
CREATE TABLE IF NOT EXISTS check_in_tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识每个任务的自增主键',
    user_id INT NOT NULL COMMENT '与用户表相关联的外键，标识创建任务的用户',
    title VARCHAR(255) NOT NULL COMMENT '用户为任务定义的标题',
    description TEXT COMMENT '任务的详细描述',
    point INT NOT NULL COMMENT '任务积分',
    status INT NOT NULL DEFAULT 1 COMMENT '任务状态：1活跃，0关闭',
    start_date DATE NOT NULL COMMENT '任务开始执行的日期',
    repeat_cycle ENUM('daily', 'weekly', 'monthly', 'yearly') NOT NULL COMMENT '任务重复的周期类型',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 创建打卡记录表
CREATE TABLE IF NOT EXISTS check_ins (
    check_in_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '打卡记录的唯一标识',
    task_id INT NOT NULL COMMENT '关联打卡任务表的外键',
    user_id INT NOT NULL COMMENT '关联用户表的外键',
    check_in_time DATE NOT NULL COMMENT '用户进行打卡的日期',
    status INT NOT NULL DEFAULT 0 COMMENT '0:未打卡，1:已打卡',
    FOREIGN KEY (task_id) REFERENCES check_in_tasks(task_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 创建积分记录表
CREATE TABLE IF NOT EXISTS point_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '积分记录的唯一标识',
    user_id INT NOT NULL COMMENT '关联用户表的外键',
    change_points INT NOT NULL COMMENT '积分的增加或减少数',
    current_points INT NOT NULL COMMENT '变动后用户的总积分',
    change_reason VARCHAR(255) NOT NULL COMMENT '积分变动的原因描述',
    change_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '积分变动的时间戳',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 创建商品表
CREATE TABLE IF NOT EXISTS items (
    item_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
    user_id INT COMMENT '用户ID',
    name VARCHAR(255) NOT NULL COMMENT '商品名称',
    description TEXT COMMENT '商品描述',
    price INT NOT NULL COMMENT '积分价格',
    stock INT NOT NULL COMMENT '库存数量',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
) ENGINE=InnoDB COMMENT='商品表';

-- 创建用户购买历史表
CREATE TABLE IF NOT EXISTS purchase_history (
    purchase_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '购买记录ID',
    user_id INT NOT NULL COMMENT '用户ID',
    item_id INT NOT NULL COMMENT '商品ID',
    quantity INT NOT NULL COMMENT '购买数量',
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '购买时间',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='用户购买历史表';