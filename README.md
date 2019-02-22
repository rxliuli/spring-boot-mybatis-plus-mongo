# 将 Mybatis/MongoDB 集成到 SpringBoot 中的示例

## 场景

> [GitHub 项目](), [Blog 教程]()

需要同时使用 `Mybatis-Plus` 和 `MongoDB`，所以就去了解了一下如何集成它们。

## 集成 Mybatis Plus

### 创建 SpringBoot 项目

使用 SpringIO 创建 SpringBoot 项目，初始依赖选择 `web`, `h2` 两个模块，gradle 配置如下

```gradle
plugins {
    id 'org.springframework.boot' version '2.1.3.RELEASE'
    id 'java'
}

apply plugin: 'io.spring.dependency-management'

group = 'com.rxliuli.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    runtimeOnly 'com.h2database:h2'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

> 注：数据库吾辈这里为了简单起见直接使用了 `H2DB`，真实项目中可能需要配置 `MySQL` 之类。为了简化项目依赖配置文件，所以使用了 Gradle 而非 Maven。

### 引入 Mybatis-Plus 和 MongoDB 依赖

在 `build.gradle` 中引入 `mybatis-plus-boot-starter` 依赖

```gradle
dependencies {
    implementation group: 'com.baomidou', name: 'mybatis-plus-boot-starter', version: '3.0.7.1'
}
```

### 添加测试数据库

在 _src/resources_ 下创建两个 sql 文件 `schema-h2.sql` 和 `data-h2.sql`，简单的使用 `H2DB` 创建数据库/表并添加数据以供测试使用。

数据库结构：`schema-h2.sql`

```sql
create schema spring_boot_mybatis_plus_mongo;
use spring_boot_mybatis_plus_mongo;

create table user_info (
  id   bigint primary key not null,
  name varchar(20)        not null,
  age  tinyint            not null,
  sex  bool               not null
);
```

数据库测试数据：`data-h2.sql`

```sql
use spring_boot_mybatis_plus_mongo;

insert into user_info (id, name, age, sex) values (1, 'rx', 17, false);
insert into user_info (id, name, age, sex) values (2, '琉璃', 18, false);
```

### 配置 Mybatis Plus

在 `application.yml` 中添加数据源配置

```yml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath*:db/schema-h2.sql
    data: classpath*:db/data-h2.sql
    url: jdbc:h2:mem:test
```

### 添加一些实体/Dao/Service

用户信息实体类：`com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo`

```java
@TableName("user_info")
public class UserInfo implements Serializable {
    @TableId
    private Long id;
    @TableField
    private String name;
    @TableField
    private Integer age;
    @TableField
    private Boolean sex;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, Integer age, Boolean sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    // getter()/setter()
}
```

用户信息 Dao：`com.rxliuli.example.springbootmybatisplusmongo.dao.UserInfoDao`

```java
@Repository
public interface UserInfoDao extends BaseMapper<UserInfo> {
}
```

用户信息业务接口：`com.rxliuli.example.springbootmybatisplusmongo.service.UserInfoService`

```java
public interface UserInfoService extends IService<UserInfo> {
}
```

用户信息业务接口实现类：`com.rxliuli.example.springbootmybatisplusmongo.service.impl.UserInfoServiceImpl`

```java
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
}
```

### 配置 Mybatis Plus 扫描的路径

在启动类配置 `Mybatis Plus`，这点非常重要，以致于吾辈要单独列出，可能会出现的问题参见 [踩坑](@#踩坑) 部分

```java
@SpringBootApplication
@MapperScan("com.rxliuli.example.springbootmybatisplusmongo.**.dao.**")
public class SpringBootMybatisPlusMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusMongoApplication.class, args);
    }
}
```

### 测试使用 Mybatis Plus 的 UserInfoService

测试 Mybatis Plus 中 `IService` 接口的 `list()` 方法

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void list() {
        final List<UserInfo> list = userInfoService.list();
        assertThat(list)
                .isNotEmpty();
    }
}
```

## 集成 MongoDB

### 引入 MongoDB Boot Starter

在 `build.gradle` 中引入 `spring-boot-starter-data-mongodb` 依赖

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
}
```

### 配置 MongoDB

在 `application.yml` 中添加 MongoDB 的配置，现在 `application.yaml` 应该变成了下面这样

```yml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath*:db/schema-h2.sql
    data: classpath*:db/data-h2.sql
    url: jdbc:h2:mem:test
  data:
    # Integration mongodb
    mongodb:
      uri: mongodb://XXX:XXX@XXX:XXX/XXX
```

### 添加 Repository

定义一些简单操作的 Dao 接口：`com.rxliuli.example.springbootmybatisplusmongo.repository.UserInfoLogRepository`

```java
@Repository
public interface UserInfoLogRepository extends MongoRepository<UserInfoLog, Long>, CustomUserInfoLogRepository {
    /**
     * 根据 id 查询用户日志信息
     *
     * @param id 查询的 id
     * @return 用户日志
     */
    UserInfoLog findUserInfoLogByIdEquals(Long id);
}
```

自定义更加复杂需求的 Dao 接口：`com.rxliuli.example.springbootmybatisplusmongo.repository.CustomUserInfoLogRepository`

```java
public interface CustomUserInfoLogRepository {
    /**
     * 根据一些参数查询用户信息列表
     *
     * @param userInfoLog 参数对象
     * @return 用户信息列表
     */
    List<UserInfoLog> listByParam(UserInfoLog userInfoLog);
}
```

具体的实现类：`com.rxliuli.example.springbootmybatisplusmongo.repository.UserInfoLogRepositoryImpl`

```java
/**
 * 数据仓库 {@link UserInfoLogRepository} 的实现类，但请务必注意，实现类继承的是 {@link CustomUserInfoLogRepository} 接口，而非本应该继承的接口
 */
public class UserInfoLogRepositoryImpl implements CustomUserInfoLogRepository {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<UserInfoLog> listByParam(UserInfoLog userInfoLog) {
        final Criteria criteria = new Criteria();
        if (userInfoLog.getUserId() != null) {
            criteria.and("userId")
                    .is(userInfoLog.getUserId());
        }
        if (userInfoLog.getLogTime() != null) {
            criteria.and("logTime")
                    .gte(userInfoLog.getLogTime());
        }
        if (userInfoLog.getOperate() != null) {
            criteria.and("operate")
                    .regex(userInfoLog.getOperate());
        }
        return mongoOperations.find(new Query(criteria), UserInfoLog.class);
    }
}
```

### 配置 MongoDB 扫描的路径

修改启动类，添加 `@EnableMongoRepositories` 注解用以配置 MongoDB 扫描的 `Repository` 路径

```java
@SpringBootApplication
@MapperScan("com.rxliuli.example.springbootmybatisplusmongo.**.dao.**")
@EnableMongoRepositories("com.rxliuli.example.springbootmybatisplusmongo.**.repository.**")
public class SpringBootMybatisPlusMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusMongoApplication.class, args);
    }
}
```

## 踩坑

1. Mybatis Plus 扫包范围  
   使用 `@MapperScan` 限制 Mybatis Plus 扫描 `Dao` 的范围，注意不要扫到 MongoDB 的 `Repository`，否则会抛出异常

   ```java
   Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException: Invalid bean definition with name 'userInfoLogRepository' defined in null: Cannot register bean definition [Root bean: class [org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] for bean 'userInfoLogRepository': There is already [Generic bean: class [org.mybatis.spring.mapper.MapperFactoryBean]; scope=singleton; abstract=false; lazyInit=false; autowireMode=2; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [D:\Text\spring-boot\spring-boot-mybatis-plus-mongo\out\production\classes\com\rxliuli\example\springbootmybatisplusmongo\repository\UserInfoLogRepository.class]] bound.
   ```

   原因是在 SpringMongoData 处理之前 Mybatis Plus 先扫描到并进行了代理，然后就会告诉你无法注册 SpringMongoData 相关的 `Repository`

2. 使用 `@EnableMongoRepositories` 限制 SpringMongoData 扫描的范围

   既然说到限制，自然也不得不说一下 SpringMongoData 本身，如果你已经使用了 `@MapperScan` 扫描 Mybatis 需要处理的 Dao，那么添加与否并不重要。但是，吾辈要说但是了，但是，如果你先使用的 MongoDB，那么如果没有使用 `@MapperScan` 处理 Mybatis 的 Dao 的话，就会抛出以下异常，所以为了安全起见还是都定义了吧

   ```java
   Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userInfoServiceImpl': Unsatisfied dependency expressed through field 'baseMapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.rxliuli.example.springbootmybatisplusmongo.dao.UserInfoDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
   ```

   说的是自动注入 `BaseMapper` 失败，实际上是因为 Mybatis 的 Dao SpringMongoData 无法处理。