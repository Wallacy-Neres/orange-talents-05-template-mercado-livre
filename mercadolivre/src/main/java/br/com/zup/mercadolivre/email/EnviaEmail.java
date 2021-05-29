package br.com.zup.mercadolivre.email;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.model.Pergunta;


@Component
public class EnviaEmail {
	public void metodoEnviaEmail(Pergunta pergunta) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				EnviaEmail.class.getPackage().getName());
		
		Mailer dispararEmail = applicationContext.getBean(Mailer.class);
		dispararEmail.enviar(new Mensagem("Nova Pergunta <testeneres1@gmail.com>", "<"+pergunta.getProduto().getDonoDoProduto().getLogin()+">", "Titulo", "Ainda tem disponivel ?"));
		
		applicationContext.close();
		System.out.println("Foi!");
	}
}
