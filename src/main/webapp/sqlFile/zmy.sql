/*
MySQL Data Transfer
Source Host: localhost
Source Database: zmy
Target Host: localhost
Target Database: zmy
Date: 2018/10/10 22:21:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for banner
-- ----------------------------
CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据自增ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '关联商品ID',
  `goods_code` varchar(16) DEFAULT NULL COMMENT '关联商品编码',
  `goods_name` varchar(32) DEFAULT NULL COMMENT '关联商品名称',
  `img_path` varchar(128) NOT NULL COMMENT 'banner图片地址',
  `status` int(11) NOT NULL COMMENT 'Banner状态 0：不轮转 1：轮转中',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `code` varchar(64) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(32) NOT NULL COMMENT '商品名称',
  `keywords` varchar(255) DEFAULT NULL COMMENT '商品关键词',
  `price` int(11) NOT NULL COMMENT '商品价格（单位：分）',
  `colors` varchar(255) DEFAULT NULL COMMENT '颜色分类',
  `longness` varchar(64) DEFAULT NULL COMMENT '长度',
  `material` varchar(64) DEFAULT NULL COMMENT '材质',
  `style` varchar(64) DEFAULT NULL COMMENT '风格',
  `env_level` varchar(64) DEFAULT NULL COMMENT '环保等级',
  `category_id` bigint(20) NOT NULL COMMENT '商品类型ID',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '商品封面图片',
  `detail` varchar(255) DEFAULT NULL COMMENT '商品详情',
  `detail_imgs` varchar(2550) DEFAULT NULL COMMENT '商品详情图片',
  `status` int(11) NOT NULL COMMENT '商品状态 1：上架 0：下架',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `title` varchar(128) NOT NULL COMMENT '公告标题',
  `content` text COMMENT '公告内容',
  `img_path` varchar(128) DEFAULT NULL COMMENT '公告图片',
  `status` int(11) NOT NULL COMMENT '公告状态 0：不展示 1：展示',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `parentId` int(11) NOT NULL COMMENT '父ID',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `element` varchar(100) NOT NULL COMMENT '权限表达式',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_element` (`element`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '据数ID',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(255) NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
CREATE TABLE `roles_permissions` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `element` varchar(255) DEFAULT NULL COMMENT '权限表达式',
  KEY `FK250AE02A41560E2` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
CREATE TABLE `tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `name` varchar(32) NOT NULL COMMENT '租户名称',
  `age` int(11) DEFAULT NULL COMMENT '租户年龄',
  `photo` varchar(255) DEFAULT NULL COMMENT '租户照片',
  `status` int(11) NOT NULL COMMENT '租户状态 1：正常 0冻结',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据自增ID',
  `token` varchar(64) NOT NULL COMMENT '用户token',
  `province_id` int(11) NOT NULL COMMENT '省份id',
  `province_name` varchar(64) DEFAULT NULL COMMENT '省份名称',
  `city_id` int(11) NOT NULL COMMENT '城市id',
  `city_name` varchar(64) DEFAULT NULL COMMENT '城市名称',
  `district_id` int(11) NOT NULL COMMENT '地区id',
  `district_name` varchar(64) DEFAULT NULL COMMENT '地区名称',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `link_man` varchar(64) NOT NULL COMMENT '联系人',
  `mobile` varchar(16) NOT NULL COMMENT '联系电话',
  `code` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `is_default` int(3) DEFAULT NULL COMMENT '是否默认 1是 0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '据数ID',
  `username` varchar(100) NOT NULL COMMENT '用户名称',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKF6CCD9C6A41560E2` (`role_id`),
  KEY `FKF6CCD9C6A418850C` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `category` VALUES ('1', '上装', 'admin', '2018-05-18 20:07:00', null);
INSERT INTO `category` VALUES ('2', '裤装', 'admin', '2018-05-18 20:08:00', null);
INSERT INTO `goods` VALUES ('1', null, '测试商品', '123', '1200', '背景透明', '120cm', '金属', '欧洲', 'E0', '1', 'http://127.0.0.1:8080/zmy-common/goods\\2018\\1010\\201810102116549620.JPEG', '123456', '0:goods\\2018\\1010\\201810102116432220.JPEG,2:goods\\2018\\1010\\201810102116433300.JPEG,1:goods\\2018\\1010\\20181010211643140.JPEG,', '1', 'admin', '2018-10-10 21:17:06', '2018-10-10 21:17:06');
INSERT INTO `goods` VALUES ('2', null, '测试商品2', '123', '1200', '背景透明', '120cm', '金属', '欧洲', 'E0', '2', '', '123', '', '1', 'admin', '2018-10-10 21:19:36', '2018-10-10 21:19:36');
INSERT INTO `goods` VALUES ('3', 'Goods201810102120113', '测试商品2', '123', '1200', '背景透明', '120cm', '金属', '欧洲', 'E0', '2', '', '123', '', '1', 'admin', '2018-10-10 21:20:11', '2018-10-10 21:20:11');
INSERT INTO `notice` VALUES ('1', '大减价', '开业大减价', null, '0', 'admin', '2015-05-23 20:00:00', '2018-05-24 23:26:08');
INSERT INTO `notice` VALUES ('3', '123', null, 'http://www.weirenfw.com/weirenService/notice\\2018\\0523\\201805232242294030.JPEG', '1', 'admin', '2018-05-23 22:43:09', '2018-05-24 23:21:52');
INSERT INTO `notice` VALUES ('4', '123', null, 'http://127.0.0.1:8080/zmy-common/notice\\2018\\0523\\201805232247286730.JPEG', '1', 'admin', '2018-05-23 22:47:30', '2018-05-24 23:25:53');
INSERT INTO `notice` VALUES ('5', '2222', null, 'http://127.0.0.1:8080/zmy-common/notice\\2018\\0523\\201805232250594230.JPEG', '1', 'admin', '2018-05-23 22:50:59', '2018-05-24 23:25:52');
INSERT INTO `permissions` VALUES ('1', '0', '系统配置管理', 'system:-', '系统配置管理');
INSERT INTO `permissions` VALUES ('2', '1', '用户管理', 'user:-', '用户管理');
INSERT INTO `permissions` VALUES ('3', '2', '查看用户列表', 'user:manage', '查看用户列表');
INSERT INTO `permissions` VALUES ('4', '2', '新增用户', 'user:add', '新增用户');
INSERT INTO `permissions` VALUES ('5', '2', '更新用户', 'user:edit', '更新用户');
INSERT INTO `permissions` VALUES ('6', '2', '删除用户', 'user:delete', '删除用户');
INSERT INTO `permissions` VALUES ('7', '2', '查看用户', 'user:view', '查看用户');
INSERT INTO `permissions` VALUES ('8', '1', '角色管理', 'role:-', '角色管理');
INSERT INTO `permissions` VALUES ('9', '8', '查看角色列表', 'role:manage', '查看角色列表');
INSERT INTO `permissions` VALUES ('10', '8', '新增角色', 'role:add', '新增角色');
INSERT INTO `permissions` VALUES ('11', '8', '更新角色', 'role:edit', '更新角色');
INSERT INTO `permissions` VALUES ('12', '8', '删除角色', 'role:delete', '删除角色');
INSERT INTO `permissions` VALUES ('13', '8', '查看角色', 'role:view', '查看角色');
INSERT INTO `permissions` VALUES ('14', '8', '角色查询', 'role:search', '角色查询');
INSERT INTO `permissions` VALUES ('15', '1', '权限管理', 'permission:-', '权限管理');
INSERT INTO `permissions` VALUES ('16', '15', '查看权限列表', 'permission:manage', '查看权限列表');
INSERT INTO `permissions` VALUES ('17', '15', '新增权限', 'permission:add', '新增权限');
INSERT INTO `permissions` VALUES ('18', '15', '更新权限', 'permission:edit', '更新权限');
INSERT INTO `permissions` VALUES ('19', '15', '删除权限', 'permission:delete', '删除权限');
INSERT INTO `permissions` VALUES ('21', '15', '查看权限', 'permission:view', '查看权限');
INSERT INTO `permissions` VALUES ('22', '2', '用户查询', 'user:search', '用户查询');
INSERT INTO `permissions` VALUES ('24', '15', '权限查询', 'permission:search', '权限查询');
INSERT INTO `permissions` VALUES ('25', '15', '初始化管理员权限', 'permission:initAdmin', '初始化系统管理员权限');
INSERT INTO `permissions` VALUES ('26', '1', '资讯管理', 'information:-', '资讯管理');
INSERT INTO `permissions` VALUES ('27', '26', '查看资讯列表', 'information:manage', '查看资讯列表');
INSERT INTO `permissions` VALUES ('28', '26', '新增资讯', 'information:add', '新增资讯');
INSERT INTO `permissions` VALUES ('29', '26', '修改资讯', 'information:edit', '修改资讯');
INSERT INTO `permissions` VALUES ('30', '26', '查看资讯', 'information:view', '查看资讯');
INSERT INTO `permissions` VALUES ('31', '26', '删除资讯', 'information:delete', '删除资讯');
INSERT INTO `permissions` VALUES ('32', '26', '是否上线资讯', 'information:change', '是否上线资讯');
INSERT INTO `permissions` VALUES ('33', '1', 'Banner管理', 'banner:-', 'Banner管理');
INSERT INTO `permissions` VALUES ('34', '33', '查看Banner列表', 'banner:manage', '查看Banner列表');
INSERT INTO `permissions` VALUES ('35', '33', '新增Banner', 'banner:add', '新增Banner');
INSERT INTO `permissions` VALUES ('36', '33', '修改Banner', 'banner:edit', '修改Banner');
INSERT INTO `permissions` VALUES ('37', '33', '删除Banner', 'banner:delete', '删除Banner');
INSERT INTO `permissions` VALUES ('38', '33', '是否允许滚动', 'banner:change', '是否允许滚动Banner');
INSERT INTO `permissions` VALUES ('39', '1', '食谱类别管理', 'cookbookType:-', '食谱类别管理');
INSERT INTO `permissions` VALUES ('40', '39', '查看食谱类别列表', 'cookbookType:manage', '查看食谱类别列表');
INSERT INTO `permissions` VALUES ('41', '39', '新增食谱类别', 'cookbookType:add', '新增食谱类别');
INSERT INTO `permissions` VALUES ('42', '39', '修改食谱类别', 'cookbookType:edit', '修改食谱类别');
INSERT INTO `permissions` VALUES ('43', '39', '查看食谱类别', 'cookbookType:view', '查看食谱类别');
INSERT INTO `permissions` VALUES ('44', '39', '删除食谱类别', 'cookbookType:delete', '删除食谱类别');
INSERT INTO `permissions` VALUES ('45', '1', '大厨管理', 'chef:-', '大厨管理');
INSERT INTO `permissions` VALUES ('46', '45', '查看大厨列表', 'chef:manage', '查看大厨列表');
INSERT INTO `permissions` VALUES ('47', '45', '新增大厨', 'chef:add', '新增大厨');
INSERT INTO `permissions` VALUES ('48', '45', '编辑大厨', 'chef:edit', '编辑大厨');
INSERT INTO `permissions` VALUES ('49', '45', '查看大厨', 'chef:view', '查看大厨');
INSERT INTO `permissions` VALUES ('50', '45', '删除大厨', 'chef:delete', '删除大厨');
INSERT INTO `permissions` VALUES ('51', '1', '区域管理', 'area:-', '区域管理');
INSERT INTO `permissions` VALUES ('52', '51', '查看区域列表', 'area:manage', '查看区域列表');
INSERT INTO `permissions` VALUES ('53', '51', '区域初始化', 'area:init', '区域初始化');
INSERT INTO `permissions` VALUES ('54', '1', '菜谱管理', 'cookbook:-', '菜谱管理');
INSERT INTO `permissions` VALUES ('55', '54', '查看菜谱列表', 'cookbook:manage', '查看菜谱列表');
INSERT INTO `permissions` VALUES ('56', '54', '新增菜谱', 'cookbook:add', '新增菜谱');
INSERT INTO `permissions` VALUES ('57', '54', '修改菜谱', 'cookbook:edit', '修改菜谱');
INSERT INTO `permissions` VALUES ('58', '54', '查看菜谱', 'cookbook:view', '查看菜谱');
INSERT INTO `permissions` VALUES ('59', '54', '删除菜谱', 'cookbook:delete', '删除菜谱');
INSERT INTO `permissions` VALUES ('60', '54', '审核菜谱状态', 'cookbook:change', '审核菜谱状态');
INSERT INTO `permissions` VALUES ('61', '1', '菜谱步骤管理', 'cookbookStep:-', '菜谱步骤管理');
INSERT INTO `permissions` VALUES ('62', '61', '查看步骤列表', 'cookbookStep:manage', '查看步骤列表');
INSERT INTO `permissions` VALUES ('63', '61', '新增菜谱步骤', 'cookbookStep:add', '新增菜谱步骤');
INSERT INTO `permissions` VALUES ('64', '45', '审核大厨状态', 'chef:change', '审核大厨状态');
INSERT INTO `permissions` VALUES ('65', '61', '步骤明显查询', 'cookbookStep:search', '步骤明显查询');
INSERT INTO `permissions` VALUES ('66', '61', '修改菜谱步骤', 'cookbookStep:edit', '修改菜谱步骤');
INSERT INTO `permissions` VALUES ('67', '61', '查看菜谱步骤', 'cookbookStep:view', '查看菜谱步骤');
INSERT INTO `permissions` VALUES ('68', '61', '删除菜谱步骤', 'cookbookStep:delete', '删除菜谱步骤');
INSERT INTO `permissions` VALUES ('69', '1', '商品管理', 'goods:-', '商品管理');
INSERT INTO `permissions` VALUES ('70', '69', '查看商品列表', 'goods:manage', '查看商品列表');
INSERT INTO `permissions` VALUES ('71', '69', '新增商品', 'goods:add', '新增商品');
INSERT INTO `permissions` VALUES ('72', '69', '修改商品', 'goods:edit', '修改商品');
INSERT INTO `permissions` VALUES ('73', '69', '查看商品', 'goods:view', '查看商品');
INSERT INTO `permissions` VALUES ('74', '69', '删除商品', 'goods:delete', '删除商品');
INSERT INTO `permissions` VALUES ('75', '69', '是否推荐商品', 'goods:recommend', '是否推荐商品');
INSERT INTO `permissions` VALUES ('76', '69', '是否上线商品', 'goods:change', '是否上线商品');
INSERT INTO `permissions` VALUES ('77', '1', '菜单管理', 'menu:-', '菜单管理');
INSERT INTO `permissions` VALUES ('78', '77', '查看菜单列表', 'menu:manage', '查看菜单列表');
INSERT INTO `permissions` VALUES ('79', '77', '新增菜单', 'menu:add', '新增菜单');
INSERT INTO `permissions` VALUES ('80', '77', '修改菜单', 'menu:edit', '修改菜单');
INSERT INTO `permissions` VALUES ('81', '77', '查看菜单', 'menu:view', '查看菜单');
INSERT INTO `permissions` VALUES ('82', '77', '删除菜单', 'menu:delete', '删除菜单');
INSERT INTO `permissions` VALUES ('83', '1', '类别管理', 'type:-', '类别管理');
INSERT INTO `permissions` VALUES ('84', '83', '查看类别列表', 'type:manage', '查看类别列表');
INSERT INTO `permissions` VALUES ('85', '83', '新增类别', 'type:add', '新增类别');
INSERT INTO `permissions` VALUES ('86', '83', '修改类别', 'type:edit', '修改类别');
INSERT INTO `permissions` VALUES ('87', '83', '查看类别', 'type:view', '查看类别');
INSERT INTO `permissions` VALUES ('88', '83', '删除类别', 'type:delete', '删除类别');
INSERT INTO `permissions` VALUES ('89', '1', '前端用户管理', 'account:-', '前端用户管理');
INSERT INTO `permissions` VALUES ('90', '89', '查看前端用户列表', 'account:manage', '查看前端用户列表');
INSERT INTO `permissions` VALUES ('91', '89', '新增前端用户', 'account:add', '新增前端用户');
INSERT INTO `permissions` VALUES ('92', '89', '修改前端用户', 'account:edit', '修改前端用户');
INSERT INTO `permissions` VALUES ('93', '89', '查看前端用户', 'account:view', '查看前端用户');
INSERT INTO `permissions` VALUES ('94', '89', '删除前端用户', 'account:delete', '删除前端用户');
INSERT INTO `permissions` VALUES ('95', '89', '是否冻结前端用户', 'account:freeze', '是否冻结前端用户');
INSERT INTO `permissions` VALUES ('96', '89', '推荐用户大厨', 'account:recommend', '推荐用户大厨');
INSERT INTO `permissions` VALUES ('97', '1', '推荐大厨管理', 'chefRecommend:-', '推荐大厨管理');
INSERT INTO `permissions` VALUES ('98', '97', '查看推荐大厨列表', 'chefRecommend:manage', '查看推荐大厨列表');
INSERT INTO `permissions` VALUES ('99', '97', '查看推荐大厨', 'chefRecommend:view', '查看推荐大厨');
INSERT INTO `permissions` VALUES ('100', '97', '删除推荐大厨', 'chefRecommend:delete', '删除推荐大厨');
INSERT INTO `permissions` VALUES ('101', '1', '商品类型管理', 'goodsType:-', '商品类型管理');
INSERT INTO `permissions` VALUES ('102', '101', '查看商品类型列表', 'goodsType:manage', '查看商品类型列表');
INSERT INTO `permissions` VALUES ('103', '101', '新增商品类型', 'goodsType:add', '新增商品类型');
INSERT INTO `permissions` VALUES ('104', '101', '修改商品类型', 'goodsType:edit', '修改商品类型');
INSERT INTO `permissions` VALUES ('105', '101', '查看商品类型', 'goodsType:view', '查看商品类型');
INSERT INTO `permissions` VALUES ('106', '101', '删除商品类型', 'goodsType:delete', '删除商品类型');
INSERT INTO `permissions` VALUES ('107', '1', '店铺管理', 'shop:-', '店铺管理');
INSERT INTO `permissions` VALUES ('108', '107', '查看店铺列表', 'shop:manage', '查看店铺列表');
INSERT INTO `permissions` VALUES ('109', '107', '店铺审核', 'shop:verify', '店铺审核');
INSERT INTO `permissions` VALUES ('110', '107', '店铺违规', 'shop:freeze', '店铺违规');
INSERT INTO `permissions` VALUES ('111', '107', '查看店铺', 'shop:view', '查看店铺');
INSERT INTO `permissions` VALUES ('112', '107', '删除店铺', 'shop:delete', '删除店铺');
INSERT INTO `permissions` VALUES ('113', '1', '订单管理', 'order:-', '订单管理');
INSERT INTO `permissions` VALUES ('114', '113', '订单管理列表', 'order:manage', '订单管理列表');
INSERT INTO `permissions` VALUES ('115', '113', '修改订单', 'order:edit', '修改订单');
INSERT INTO `permissions` VALUES ('116', '113', '查看订单', 'order:view', '查看订单');
INSERT INTO `permissions` VALUES ('117', '113', '删除订单', 'order:delete', '删除订单');
INSERT INTO `permissions` VALUES ('118', '1', '业务人员管理', 'servicer:-', '业务人员管理');
INSERT INTO `permissions` VALUES ('119', '118', '查看业务人员列表', 'servicer:manage', '查看业务人员列表');
INSERT INTO `permissions` VALUES ('120', '118', '新增业务人员', 'servicer:add', '新增业务人员');
INSERT INTO `permissions` VALUES ('121', '118', '修改业务人员', 'servicer:edit', '修改业务人员');
INSERT INTO `permissions` VALUES ('122', '118', '查看业务人员', 'servicer:view', '查看业务人员');
INSERT INTO `permissions` VALUES ('123', '118', '删除业务人员', 'servicer:delete', '删除业务人员');
INSERT INTO `permissions` VALUES ('124', '118', '业务人员所推荐人列表', 'servicer:referee', '业务人员所推荐人列表');
INSERT INTO `permissions` VALUES ('125', '1', '提现记录管理', 'shopWithdraw:-', '提现记录管理');
INSERT INTO `permissions` VALUES ('126', '125', '提现记录列表管理', 'shopWithdraw:manage', '提现记录列表管理');
INSERT INTO `permissions` VALUES ('127', '125', '提现记录更新', 'shopWithdraw:update', '提现记录更新');
INSERT INTO `permissions` VALUES ('128', '125', '查看提现记录', 'shopWithdraw:view', '查看提现记录');
INSERT INTO `permissions` VALUES ('129', '125', '删除提现记录', 'shopWithdraw:delete', '删除提现记录');
INSERT INTO `roles` VALUES ('1', '系统管理员', '系统管理员拥有系统所有权限');
INSERT INTO `roles` VALUES ('2', '用户管理员', '负责用户的管理、新增、更新、查询与删除操作');
INSERT INTO `roles` VALUES ('3', '一体机升级管理', '一体机软件版本升级管理');
INSERT INTO `roles` VALUES ('4', '游戏管理员', '游戏推荐标记/游戏管理/游戏类型管理');
INSERT INTO `roles_permissions` VALUES ('2', 'user:view');
INSERT INTO `roles_permissions` VALUES ('2', 'user:add');
INSERT INTO `roles_permissions` VALUES ('2', 'user:delete');
INSERT INTO `roles_permissions` VALUES ('2', 'user:-');
INSERT INTO `roles_permissions` VALUES ('2', 'user:edit');
INSERT INTO `roles_permissions` VALUES ('2', 'user:manage');
INSERT INTO `roles_permissions` VALUES ('2', 'user:search');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:manage');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:add');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:change');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:-');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:view');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:delete');
INSERT INTO `roles_permissions` VALUES ('3', 'apkVr:edit');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:search');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:add');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:edit');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:add');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:edit');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:delete');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:view');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:-');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:view');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:view');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:manage');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:edit');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:delete');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:-');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:delete');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:add');
INSERT INTO `roles_permissions` VALUES ('4', 'gameInfo:change');
INSERT INTO `roles_permissions` VALUES ('4', 'gameMark:manage');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:-');
INSERT INTO `roles_permissions` VALUES ('4', 'gameType:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:view');
INSERT INTO `roles_permissions` VALUES ('1', 'type:view');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:change');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'order:-');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:-');
INSERT INTO `roles_permissions` VALUES ('1', 'account:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'user:-');
INSERT INTO `roles_permissions` VALUES ('1', 'information:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:view');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:change');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:view');
INSERT INTO `roles_permissions` VALUES ('1', 'type:-');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:add');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:change');
INSERT INTO `roles_permissions` VALUES ('1', 'area:init');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:view');
INSERT INTO `roles_permissions` VALUES ('1', 'order:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:search');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:view');
INSERT INTO `roles_permissions` VALUES ('1', 'account:-');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:view');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:-');
INSERT INTO `roles_permissions` VALUES ('1', 'chefRecommend:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'chefRecommend:view');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'account:add');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'type:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'shopWithdraw:update');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:add');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:search');
INSERT INTO `roles_permissions` VALUES ('1', 'shopWithdraw:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:add');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:change');
INSERT INTO `roles_permissions` VALUES ('1', 'order:view');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:-');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:-');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:-');
INSERT INTO `roles_permissions` VALUES ('1', 'user:add');
INSERT INTO `roles_permissions` VALUES ('1', 'user:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:add');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:recommend');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:add');
INSERT INTO `roles_permissions` VALUES ('1', 'system:-');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:-');
INSERT INTO `roles_permissions` VALUES ('1', 'user:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:verify');
INSERT INTO `roles_permissions` VALUES ('1', 'type:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'information:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'role:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'area:-');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:view');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:-');
INSERT INTO `roles_permissions` VALUES ('1', 'user:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:-');
INSERT INTO `roles_permissions` VALUES ('1', 'role:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'area:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'account:recommend');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'order:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:view');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookStep:-');
INSERT INTO `roles_permissions` VALUES ('1', 'information:change');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'information:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:-');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'role:search');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'type:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'servicer:referee');
INSERT INTO `roles_permissions` VALUES ('1', 'order:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'account:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'account:freeze');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'user:view');
INSERT INTO `roles_permissions` VALUES ('1', 'chefRecommend:-');
INSERT INTO `roles_permissions` VALUES ('1', 'type:add');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbook:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'goods:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'information:add');
INSERT INTO `roles_permissions` VALUES ('1', 'shopWithdraw:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'account:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:initAdmin');
INSERT INTO `roles_permissions` VALUES ('1', 'information:-');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'role:view');
INSERT INTO `roles_permissions` VALUES ('1', 'account:view');
INSERT INTO `roles_permissions` VALUES ('1', 'role:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'user:search');
INSERT INTO `roles_permissions` VALUES ('1', 'information:view');
INSERT INTO `roles_permissions` VALUES ('1', 'chefRecommend:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'banner:edit');
INSERT INTO `roles_permissions` VALUES ('1', 'chef:view');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:view');
INSERT INTO `roles_permissions` VALUES ('1', 'cookbookType:delete');
INSERT INTO `roles_permissions` VALUES ('1', 'role:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'menu:add');
INSERT INTO `roles_permissions` VALUES ('1', 'shopWithdraw:view');
INSERT INTO `roles_permissions` VALUES ('1', 'shopWithdraw:-');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:freeze');
INSERT INTO `roles_permissions` VALUES ('1', 'permission:manage');
INSERT INTO `roles_permissions` VALUES ('1', 'goodsType:-');
INSERT INTO `roles_permissions` VALUES ('1', 'role:-');
INSERT INTO `roles_permissions` VALUES ('1', 'shop:delete');
INSERT INTO `tenant` VALUES ('1', '张三', '182', 'http://127.0.0.1:8080/zmy-common/tenant\\2018\\0525\\201805251718265070.PNG', '1', 'admin', '2018-05-25 16:07:39', '2018-05-25 17:18:26');
INSERT INTO `user_address` VALUES ('1', '1231231', '1', null, '1', null, '1', null, null, '1', '1', '1', null);
INSERT INTO `users` VALUES ('1', 'admin', '117625153@qq.com', '6620dbfab04123dc9624a001b101f4c94a67f94e2ba95e9d64db14f8e7b45f84');
INSERT INTO `users` VALUES ('2', 'zmy', '110@qq.com', '46d65ff883e75bbf297870d37536972a0bac45bddd95a236a7eb97886bfe216b');
INSERT INTO `users` VALUES ('3', 'zmj', 'zmj@qq.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO `users` VALUES ('4', 'ids', '', '1664e03fcaea70a94736def3f0ee8e0a20adc74eb9f1d1262d7a814c371405ab');
INSERT INTO `users` VALUES ('8', 'admin1', '117625153@qq.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
INSERT INTO `users_roles` VALUES ('1', '1');
INSERT INTO `users_roles` VALUES ('8', '1');
INSERT INTO `users_roles` VALUES ('2', '2');
INSERT INTO `users_roles` VALUES ('3', '2');
INSERT INTO `users_roles` VALUES ('1', '3');
INSERT INTO `users_roles` VALUES ('4', '3');
