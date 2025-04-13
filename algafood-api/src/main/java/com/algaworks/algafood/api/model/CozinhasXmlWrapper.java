package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

//@Data
//@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {
	
	//@NonNull
	//@JacksonXmlProperty(localName = "cozinha")
	//@JacksonXmlElementWrapper(useWrapping = false)
	public List<Cozinha> cozinhas;

}
