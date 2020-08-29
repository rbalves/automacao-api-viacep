# Automação API ViaCEP
Projeto de automação desenvolvido usando JUnit, Cucumber e Rest-assured.

## Funcionalidades
A automação desenvolvida neste projeto contempla quatro cenários: 
1. no primeiro cenário, é possível testar o retorno de uma consulta com CPF válido.
2. no segundo cenário, é possível testar o retorno de uma consulta com CPF inexistente.
3. no terceiro cenário, é possível testar o retorno de uma consulta com CPF inválido.
4. no quarto cenário, é possível testar o retorno de uma consulta personalizada com UF, localidade e logradouro.

## Preparando o ambiente

Para executar o projeto, foi necessário instalar e configurar os seguintes programas:

- [JDK](https://www.oracle.com/technetwork/java/javase/14-0-1-relnotes-5972653.html)
	
Após a instalação do JDK, foi necessário executar as seguintes etapas:
1. Criar a variável JAVA_HOME com o caminho JDK padrão, que geralmente é C: \ Arquivos de Programas \ Java \ <versão do jdk>
2. Editar a variável Path adicionando% JAVA_HOME% \ bin;
3. Confirmar a instalação usando o comando java -version

- [Eclipse](https://www.eclipse.org/downloads/packages/)

## Criando e executando o projeto
Um projeto Maven padrão foi criado. As dependências são gerenciadas pelo Maven através do arquivo pom.xml.
Os trechos XML das instalações foram consultadas em [MVNRepository](https://mvnrepository.com/).

Para executar as etapas de teste, dentro da classe Runner.java, é necessário  acessar Run > Run as > JUnit Test.

## Estrutura do projeto
Além dos pacotes e arquivos padrões, é importante destacar alguns elementos importantes:
1. **src/main/java**: neste local, foi adicionado o pacote "core" que possui a classe "Local.java", usado para instanciar valores de uma lista mockada.
2. **src/test/java**: neste local, foram criados dois pacotes ("runners" e "steps"):
	- No primeiro pacote, foi adicionada uma classe que seta os parâmetros Cucumber e inicia os testes (Runner.java)
	- No segundo pacote, foi adicionada uma classe com os steps da feature (ConsultaCepSteps.java)
3. **src/test/resources**: Este pacote possui uma pasta chamada "features", que por sua vez contém os arquivos com a extensão .feature. Estes arquivos
                       possuem a lista de passos que devem ser executados, seguindo o padrão BDD.

## Dependências
- REST Assured 4.0.0
- JUnit 4.12
- Cucumber Java 1.2.6
- Cucumber JUnit 1.2.6
