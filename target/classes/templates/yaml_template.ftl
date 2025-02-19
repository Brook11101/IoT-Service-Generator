spring:
  datasource:  #配置数据库信息
   driver-class-name: com.mysql.cj.jdbc.Driver   #mysql驱动
   url: jdbc:mysql://114.55.74.144:3306/logactivity?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&allowMultiQueries=true&allowPublicKeyRetrieval=true
   username: root    #数据库名称
   password: Root123456.  #数据库密码
druid:
  timeBetweenEvictionRunsMillis: 60000
  minEvictableIdleTimeMillis: 300000
  validationQuery: SELECT 1
  testWhileIdle: true
  testOnBorrow: false
  testOnReturn: false
  keepAlive: true
#配置mybatis-plus
mybatis-plus:
#告诉mybatis框架映射文件的位置
   mapper-locations: classpath*:com/applet/mpinterface/mapper/**/*.xml
#设置类型别名，在映射文件中可以直接写首字母小写的实体类名称即可
   type-aliases-package: com.applet.mpinterface.domain.pojo
#驼峰映射：例如数据库中的字段名emp_name能对应实体类中的属性名empName
configuration:
  map-underscore-to-camel-case: true
logging:
level:
  org:
    springframework=DEBUG:
server:
   port: ${portNum?c}  # 传递的端口号参数
gp:
  rpc:
    servicePort: 20880
    registerType: 0
    registryAddress: 114.55.74.144:2181