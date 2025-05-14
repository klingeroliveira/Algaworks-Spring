insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_Frete, cozinha_id) values ('Food', 3.43, 1);
insert into restaurante (nome, taxa_Frete, cozinha_id) values ('Algafood', 2.54, 2);


insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Cartao Debito');
insert into forma_pagamento (descricao) values ('Cartao Credito');

insert into estado (id, uf, nome) values (1, 'ES', 'Espirito Santo');
insert into estado (id, uf, nome) values (2, 'MS', 'Mato Grosso do Sul');
insert into estado (id, uf, nome) values (3, 'DF', 'Distrito Federal');

insert into cidade (nome, estado_id) values ('Vila Velha', 1);
insert into cidade (nome, estado_id) values ('Campo Grande', 2);
insert into cidade (nome, estado_id) values ('Brasilia', 3);

insert into permissao (nome, descricao) values ('Consultar Cidades', 'Permite a consulta na tabela de estados.');
insert into permissao (nome, descricao) values ('Consultar Restaurantes', 'Permite a consulta na tabela de restaurentes.');
insert into permissao (nome, descricao) values ('Alterar Permissoes', 'Permite as permissoes cadastradas.');