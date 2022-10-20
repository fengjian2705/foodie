## profiles 多环境配置配置梳理：
1. 数据源配置
   1.1 url 根据自身情况去修改为 localhost 或者内网 ip（集群或分布式系统一定要使用内网 ip）
   1.2 密码 root 改为你自己的密码
2. mybatis 日志打印
   dev     可以打印
   test    可以打印
   prod    无需打印
3. 图片保存目录和图片服务请求路径配置
   /workspaces/images/foodie/faces
   http://api.z.mukewang.com:8088/foodie-dev-api/foodie/faces
4. 从支付中心回调天天吃货后端服务回调地址
5. 打包方式
   * jar：服务化的概念，后续接触 SpringCloud，所有的服务都是以 jar 的方式
   * war：应用程序的概念，也可以向外提供服务和接口