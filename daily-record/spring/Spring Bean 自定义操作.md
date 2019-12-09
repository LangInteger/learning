# Customizing the Nature of a Bean

The Spring Framework provides a number of interfacs you can use to customize the nature of a bean.

- Lifecycle Callbacks
- ApplicationContextAware and BeanNameAware
- Other Aware interfaces

## 1 Lifecycle Callbacks

### Initialization callback: Bean 初始化（DI 完成后）后植入某些操作

- Implement `InitializingBean` with overriding `afterPropertiesSet` method to let the bean perform certain actions upon initialization
- 使用 @PostConstruce 注解
- 在 bean definition 元数据中配置 init-method

### Destruction Callbacks: 容器被摧毁前（when the container that contains it is destroyed）植入某些操作

- Implement `DisposableBean` with overriding `destroy` method to let the bean perform certain actions upon destruction
- 使用 @PreDestroy 注解
- 在 bean definition 元数据中配置 destroy-method

Spring 内部则是使用 `BeanPostProcessor` 来进行 bean 初始化过程中的回调，要想进行一些自定义操作，也可以自己实现 BeanPostProcessor 接口定义一些操作。

Spring container 保证 Initialization callback 在 bean 的依赖注入完成后马上被调用，即这些 Callback 都是在 raw bean reference 上进行的，AOP interceptors 还未施加于 bean 上。

### Lifecycle mechanism 优先级

Mutiple lifycycle mechanisms configured for the same bean, with different initialization methods, are called as follows:

- Methods annotated with `@PostConstruct`
- `afterPropertiesSet()` as defined by the `InitializingBean` callback interface
- A custom configured `init()` method

Destroy methods are called in the same order:

- Method annotated with `@PreDestroy`
- `destroy()` as defined by the `DisposableBean` callback interface
- A custom configured `destroy()` method
