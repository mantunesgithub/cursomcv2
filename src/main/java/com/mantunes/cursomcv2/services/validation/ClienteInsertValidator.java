package com.mantunes.cursomcv2.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.mantunes.cursomcv2.DTO.ClienteNewDTO;
import com.mantunes.cursomcv2.domain.enums.TipoCliente;
import com.mantunes.cursomcv2.repositories.ClienteRepository;
import com.mantunes.cursomcv2.resources.exceptions.FieldMessage;
import com.mantunes.cursomcv2.services.validation.utils.BR;

/*
 * Validator
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		
		if	(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&
			!BR.isValidCPF(objDto.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
		}
		if	(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&
				!BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
		}
		for (FieldMessage e : list) {
			 context.disableDefaultConstraintViolation();
			 context.buildConstraintViolationWithTemplate(e.getMessage())
			 .addPropertyNode(e.getFieldName()).addConstraintViolation();
		 }
		return list.isEmpty();
	}
}	