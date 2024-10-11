-- 积分表
CREATE TABLE t_point (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识',
                         user_id BIGINT NOT NULL COMMENT '关联用户表的外键',
                         points BIGINT NOT NULL COMMENT '总积分',
                         creator_id bigint DEFAULT NULL COMMENT '创建者ID',
                         create_time datetime DEFAULT NULL COMMENT '创建时间',
                         updater_id bigint DEFAULT NULL COMMENT '更新者ID',
                         update_time datetime DEFAULT NULL COMMENT '更新时间',
                         deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
) COMMENT='积分表';

-- 打卡任务表
CREATE TABLE IF NOT EXISTS t_check_in_tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识每个任务的自增主键',
    user_id BIGINT NOT NULL COMMENT '与用户表相关联的外键，标识创建任务的用户',
    title VARCHAR(255) NOT NULL COMMENT '用户为任务定义的标题',
    description TEXT COMMENT '任务的详细描述',
    point INT NOT NULL COMMENT '任务积分',
    status INT NOT NULL DEFAULT 1 COMMENT '任务状态：1活跃，0关闭',
    start_date DATE NOT NULL COMMENT '任务开始执行的日期',
    repeat_cycle INT COMMENT '任务重复的周期类型',
    creator_id bigint DEFAULT NULL COMMENT '创建者ID',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    updater_id bigint DEFAULT NULL COMMENT '更新者ID',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
    ) COMMENT='打卡任务表';

-- 打卡记录表
CREATE TABLE IF NOT EXISTS t_check_ins (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '打卡记录的唯一标识',
                                           task_id BIGINT NOT NULL COMMENT '关联打卡任务表的外键',
                                           user_id BIGINT NOT NULL COMMENT '关联用户表的外键',
                                           check_in_time DATE NOT NULL COMMENT '用户进行打卡的日期',
                                           status INT NOT NULL DEFAULT 0 COMMENT '0：未打卡，1：已打卡',
                                           expiry_time TIMESTAMP NOT NULL COMMENT '打卡任务的过期时间',
                                           creator_id bigint DEFAULT NULL COMMENT '创建者ID',
                                           create_time datetime DEFAULT NULL COMMENT '创建时间',
                                           updater_id bigint DEFAULT NULL COMMENT '更新者ID',
                                           update_time datetime DEFAULT NULL COMMENT '更新时间',
                                           deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
    ) COMMENT='打卡记录表';

-- 积分记录表
CREATE TABLE IF NOT EXISTS t_point_records (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '积分记录的唯一标识',
                                               user_id BIGINT NOT NULL COMMENT '关联用户表的外键',
                                               change_points INT NOT NULL COMMENT '积分的增加或减少数',
                                               current_points INT NOT NULL COMMENT '变动后用户的总积分',
                                               change_reason VARCHAR(255) NOT NULL COMMENT '积分变动的原因描述',
    creator_id bigint DEFAULT NULL COMMENT '创建者ID',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    updater_id bigint DEFAULT NULL COMMENT '更新者ID',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
    ) ENGINE = InnoDB COMMENT='积分记录表';

-- 商品表
CREATE TABLE IF NOT EXISTS t_items (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
                                       user_id BIGINT COMMENT '用户ID',
                                       name VARCHAR(255) NOT NULL COMMENT '商品名称',
    description TEXT COMMENT '商品描述',
    price INT NOT NULL COMMENT '积分价格',
    stock INT NOT NULL COMMENT '库存数量',
    creator_id bigint DEFAULT NULL COMMENT '创建者ID',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    updater_id bigint DEFAULT NULL COMMENT '更新者ID',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
    ) COMMENT='商品表';

-- 用户购买历史
CREATE TABLE IF NOT EXISTS t_purchase_history (
                                                  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '购买记录ID',
                                                  user_id BIGINT NOT NULL COMMENT '用户ID',
                                                  item_id BIGINT NOT NULL COMMENT '商品ID',
                                                  quantity INT NOT NULL COMMENT '购买数量',
                                                  purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '购买时间',
                                                  creator_id bigint DEFAULT NULL COMMENT '创建者ID',
                                                  create_time datetime DEFAULT NULL COMMENT '创建时间',
                                                  updater_id bigint DEFAULT NULL COMMENT '更新者ID',
                                                  update_time datetime DEFAULT NULL COMMENT '更新时间',
                                                  deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除'
    ) COMMENT='用户购买历史表';
