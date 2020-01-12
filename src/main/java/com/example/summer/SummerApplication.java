package com.example.summer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SummerApplication {

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("10240MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400MB");
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(SummerApplication.class, args);
	}
}
