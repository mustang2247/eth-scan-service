package com.open.coinnews;

import com.open.coinnews.basic.tools.ConfigTools;
import com.open.coinnews.service.ScheduledTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Slf4j
@SpringBootApplication
@EnableScheduling
public class StartApplication implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Autowired
    private ConfigTools configTools;

    private static Integer startBlockNumber = 0;

    public static void main(String [] args) {
        if( args.length > 1 ) {
//            startBlockNumber:5488000
            String[] addr = args[0].split( ":", 2 );
            Integer startNum = Integer.valueOf(addr[1]);
            if (startNum > 1)
            {
                startBlockNumber = startNum;
            }
        }

        logger.info("start startBlockNumber: " + startBlockNumber);
        SpringApplication.run(StartApplication.class, args);


    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            }
        };
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    @Override
    public void run(String... strings) throws Exception {
//        log.info("start bolt");
        if (startBlockNumber > 1)
        {
            configTools.setStartBlockNumber(startBlockNumber);
        }

        logger.info("启动扫描 ");
        scheduledTaskService.startBlockChainScan();
    }
}
