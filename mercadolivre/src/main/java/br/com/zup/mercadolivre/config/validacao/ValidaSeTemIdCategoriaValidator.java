package br.com.zup.mercadolivre.config.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaSeTemIdCategoriaValidator implements ConstraintValidator<ValidaSeTemIdCategoriaValue, Object>{

	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ValidaSeTemIdCategoriaValue params) {
		domainAttribute = params.fieldName();
		klass = params.domaiClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " +klass.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		if(!list.isEmpty() || value==null){
			return true;
		}else return false;
	}
}
