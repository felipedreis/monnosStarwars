Projeto implementado a partir do desafio que está descrito no [PDF](https://github.com/kevinbg012/starwars-project/blob/master/BackEndJava-API.pdf)

**Como executar o projeto:**

<ul>
<li>Vá para a pasta raiz do projeto</li>
<li>mvn clean install</li>
<li>docker-compose up</li>
</ul>

Caso faça alterações de código posteriormente:

<ul>
<li>Vá para a pasta raiz do projeto</li>
<li>mvn clean install</li>
<li>docker-compose up --build</li>
</ul>

Realizar fork desse projeto para que verifiquemos as mudanças realizadas em seu repositório.

**Tarefa:**

Ao realizar busca dos planetas por API, guardar valores da busca realizada no banco local para que possam ser usados posteriormente.
Lembrando que casos colisões precisam ser tratados. Valores vindos da API tem preferência em relação aos valores armazenados localmente.
