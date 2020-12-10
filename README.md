Projeto de avaliação técnica
Arquitetura: MVC, JAVA EE / JSF / PrimeFaces, Hibernate / HQL
Banco de dados: Postgresql
IDE: Redhat 12.13.0 GA
Servidor: Wildfly 10.1
Programador: Geferson Buzzello



ITENS REALIZADOS:

-Os itens 1, 2 e 3 estão definidos em script.sql, para executar use: psql -U postgres -h 127.0.0.1 -d <database> -f script.sql
-Os itens 4 a 8 referem-se ao projeto MVC desenvolvido e possuem opções de menu para executa-los
-O item 9 esta definido no projeto na classe main: com.pack.main.Primos
-O item 10 também está no projeto na opção de menu: Principal / Lançamentos / Consultas / Opção : Valor Médio
-O item 11 também está no projeto na opção de menu: Principal / Lançamentos / Consultas / Opção : Maiores Valores
-O item 12 está definido em update.sql, para executar use: psql -U postgres -h 127.0.0.1 -d <database> -f update.sql


 


Configuração de ambiente.

Para executar a aplicação em um ambiente de desenvolvimento é necessário configurar o servidor JBoss/Wildfly, e fazer o deploy do driver Postgres que está junto com os fontes em 
.\WebContent\WEB-INF\lib\postgresql-9.4.1209.jar.

Em seguida configurar no standalone.xml os itens:

	<system-properties>
		<property name="lincros.jndi" value="java:jboss/datasources/desafio"/>
	</system-properties>

	...

	<datasource jta="true" jndi-name="java:jboss/datasources/desafio" pool-name="desafio-l" enabled="true" use-ccm="true">
		<connection-url>jdbc:postgresql://127.0.0.1:5432/desafio</connection-url>
		<driver-class>org.postgresql.Driver</driver-class>
		<driver>postgres.jar</driver>
		<security>
			<user-name>postgres</user-name>
			<password>1</password>
		</security>
	</datasource>
	*Substituir o 127.0.0.1 pelo endereço do servidor postgres.


	...

	<spec-descriptor-property-replacement>true</spec-descriptor-property-replacement>

	....
	<interfaces>
		<interface name="management">
			<any-address/>
		</interface>
		<interface name="public">
			<any-address/>
		</interface>
	</interfaces>
	*interfaces só é necessário se desejar acesso externo


No postgres:

-Para a criação das tabelas execute o script.sql:
	psql -U postgres -h 127.0.0.1 -d <database> -f script.sql

No Eclipse:

-Importar o projeto e configurar o JRE System Library se necessário.
-Fazer o deploy no server e iniciar o servidor
-A URL para acesso é localhost:8080/lincros



