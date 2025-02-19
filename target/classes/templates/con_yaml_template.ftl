serviceAddress: 192.168.50.116
servicePort: 20880

registryType: 0
registryAddress: 114.55.74.144:2181

spring:
  datasource:  #配置数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver   #mysql驱动
    url: jdbc:mysql://114.55.74.144:3306/logactivity?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&allowMultiQueries=true&allowPublicKeyRetrieval=true
    username: root    #数据库名称
    password: Root123456.  #数据库密码
server:
  port: ${portNum?c}  # 传递的端口号参数