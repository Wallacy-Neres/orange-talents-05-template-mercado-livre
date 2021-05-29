package br.com.zup.mercadolivre.email;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Teste {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				Teste.class.getPackage().getName());
		
		Mailer mailer = applicationContext.getBean(Mailer.class);
		mailer.enviar(new Mensagem("Neres Test <testeneres1@gmail.com>", "Aula Email <wallacyc11@gmail.com>", "Vai logo envia ai", "Vamos ver"));
		
		applicationContext.close();
		System.out.println("Foi!");
	}
}
