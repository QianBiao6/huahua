package com.huahua.spit.article;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import huahua.common.utils.IdWorker;
@SpringBootApplication
public class ArticleaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleaApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
