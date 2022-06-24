# Redes-TP

# Instruções para rodar o projeto
1. Clone esse repositorio
2. Entre na pasta Projeto
3. Rode o comando mvn clean install
4. Rode a classe ChatServer.java. Atente-se para a porta do servidor sendo configurada.
5. Rode a classe HelloApplication.java. Atente-se ao IP e porta do servidor.
6. Para que a comunicação funcione, abra dois clientes ou configure o cliente para mandar mensagem para ele mesmo.

# Observações
* Os clientes (classe HelloApplication.java) só funcionam localmente. Caso o objetivo seja conversar com um cliente em outra máquina, uma pequena modificação na mensagem enviada deveria ser feita para enviar, alem das portas, o IP do cliente destino.
* O servidor pode estar tanto local quanto em outra máquina
