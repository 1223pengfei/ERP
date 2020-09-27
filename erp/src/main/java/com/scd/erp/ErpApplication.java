package com.scd.erp;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.PropertyConfigurator;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableCaching // 开启缓存
@EnableTransactionManagement // 开启事务，保证redis与mysql中数据的一致性
@MapperScan("com.scd.erp.mapper")
@Component
public class ErpApplication {
    private static final Logger logger = LoggerFactory.getLogger(ErpApplication.class);

    @Value("${server.port}")
    private int port;
    @Value("${isDevelop}")
    private static boolean isdevelop;

    static {

        try{
            // 初始化log4j
            String log4jPath = ResourceUtils.getURL("classpath:").getPath()+"log4j.properties";
            logger.info("Log4j线下开发模式初始化。。。");
            logger.info("初始化Log4j。。。。");
            logger.info("path is "+ log4jPath);
            PropertyConfigurator.configure(log4jPath);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    public static void main(String[] args) {
        final String[] temp = args;
        //DbSchemaCreate.main(temp);
        logger.info("oops: main入口函数编码-" +System.getProperty("file.encoding"));
        if(isdevelop){
            logger.info("develop is："+isdevelop);
            logger.info("oops:" + args[0]);
        }else {
            SpringApplication.run(ErpApplication.class, args);
            logger.info("develop is："+isdevelop);
            logger.info("oops: 线下开发测试");
        }
        if(ArrayUtils.isNotEmpty(args))
        {
            // 如果你的应用程序，运行后不自动退出，那么处理start时不要执行正常的代码，否则在部署测试 appctl.sh 的时候，会一直等待进程退出
            if(args[0].equals("startup"))
            {
                new Thread(
                        new Runnable(){
                            public void run(){
                                System.out.println("启动Mythread子线程");
                                logger.info("启动Mythread子线程");
                                SpringApplication.run(ErpApplication.class, temp);
                            }
                        }).start();

                System.out.println("program startup");
                logger.info("program startup");
            }else if(args[0].equals("stop"))
            {
                System.out.println("program stop");
                logger.info("program stop");
            }else if(args[0].equals("restart"))
            {
                System.out.println("program restart");
                logger.info("program restart");
            }else if(args[0].equals("status"))
            {
                System.out.println("program status");
                logger.info("program status");
            }
        }
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return strings -> {
            System.err.println("Number of process definitions : "
                    + repositoryService.createProcessDefinitionQuery().count());
            System.err.println("Number of tasks : " + taskService.createTaskQuery().count());

        };
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
