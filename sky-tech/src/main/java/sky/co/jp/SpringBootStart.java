package sky.co.jp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
    @MapperScan(value = "sky.co.jp.mapper")
    public class SpringBootStart {
    public static void main(String[] args){
        SpringApplication.run(SpringBootStart.class, args);
    }
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer Container) {
                //セッション生存時間30分
                Container.setSessionTimeout(30*60);
            }
        };
    }
}
