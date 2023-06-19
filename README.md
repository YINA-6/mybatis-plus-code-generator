# Mybatis-Plus代码快速构建工具类通用版

## 配置介绍
1. 代码构建由 MPCodeGenerator 自定义配置完成。
2. 工具类配置了controller、service、model、mapper相关策略。
3. controller 默认开启生成 @RestController 控制器。
4. service 默认继承 IService.class, 父类默认继承 ServiceImpl.class。
5. mapper 默认继承 BaseMapper.class。

## 使用介绍
1. 在启用工具类前，请配置您的数据库信息。
2. 在启用工具类前，请按您的需求，更改工具类的策略配置和生成的表名。


更多自定义配置请查看官网: https://baomidou.com/pages/981406/


