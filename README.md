# Sistema de aprovação remota de solicitações efeituada no pdv da loja 01 do mercado borba

## Contexto

A demando do projeto surgiu a partir de uma solicitação feita pelos gestores da loja, na qual o sistem de frente de caixa (pdv) não oferece um recurso nativo para a realização de liberações remotas de solicitações. Liberações essas que solicitam a matricula e a senha do fiscal/ gerente para permitir ações como: cancelamento de vendas, excluir itens de uma compra, realização de retirada de dinheiro ou abertura manual da gaveta.

Por tanto, a ideia é crir um sistema no qual os fiscais, gesteres e gerentes consigam aprovar uma solicitação remotamente, e apos a liberacao o sistema consiga simular um teclado e injetar texto como se o usuario que realizou a liberacao estivesse digitando ali na hora de realizar a autorização.

## Levantamento de requisitos solicitados

1. O sistema deve conter uma interface web em que o usuario solicitante pode abrir um novo pedido de aprovação e o usuario aprovador consegue aprovar ou negar essa solicitação;
2. Cada requisição deve ter um periodo de validade de 5 minutos;
3. Toda nova requisição deve ser comunicada para as pessoas com permisão para realizar aquela ação, informando que existe uma nova requisição para ser aprovada;
4. O usuario solicitante deve informar qual é o tipo de solicitação (libera cancelamento de venda, libera cancelamento de item, libera desconto, libera retirada, libera suprimento, libera credito rotativo), tambem será solicitado o motivo daquela liberação para as seguintes modalidade:

   Libera cancelamento de venda ou Libera cancelamento de item:
   1 - Devolução de mercadoria;
   2 - Erro de registro;
   3 - Dinheiro do cliente insuficiente;
   4 - Produto com preço errado
   5 - Teste de equipamento;
   6 - Cheque do cliente recusado;
   7 - Cartão recusado ou sem saldo;
   8 - Problemas no equipamento;

   Caso seja solicitado o cancelamento de item deverá ser solicitado o numero o item a ser cancelado;

Libera retirada:
1 - Retirada;
2 - Retirada cf;

Libera credito rotativo:
1 - Conta vencida;
2 - Limite excedido;

      Após isso será solicitado o codigo do cliente, o nome do cliente e, se for por limite excedido será solicitado o valor excedido;

5. Sobre a liberação automatica, o cliente solicitou que houvesse uma parametrização na aplicação que permite definir horarios em que as solicitações terão aprovação automatica, ou seja não será necessario a aprovação de nenhum fiscal (QUESTÃO: 1 - SERÁ UTILIZADO QUAL MATRICULA E SENHA? 2 - ESSA PARAMETRIZAÇÃO É PARA TODOS OS DIAS, OU DEVE PERMITIR O CADASTRO DE DIAS EM QUE ELA É VALIDA? 3 - DEVERA PERMITIR O CADASTRO DE UMA PARAMETRIÇÃO PARA UM DIA ESPECIFIFO?);

6. Apenas usuarios com previlegios para aprovar aquela solicitação podem aprovar aquela solicitação, o usuario aprovador deverá visualizar apenas as solicitações as quais ele tem permissão para autorizar;

7. Todos os usuários deve ter acesso tanto ao menu de enviar solicitação quando ao de aprovar solicitação, sendo foi solicitado que todas as operações realizadas precisa obrigatoriamente passar a matricula e senha do usuario, seja no lancamento ou na aprovação;

8. O usuario solicitante deve ver em tempo real o status da sua solicitação, bem com o tempo de expiração;

9. Foi solicitado que, na tela de solicitações enviadas, seja exibidas apenas as solicitações do usuário logado, permitindo filtrar por solicitações em aberto, canceladas, aprovadas e expiradas (sendo por padrao mostrar apenas as em abertos);

10. Um usuário não pode ter mais de uma solicitação em andamento ao mesmo tempo, bem como só pode haver uma solicitação em aberto para um caixa. Caso uma solicitação seja feita equivocadamente, para a abertura de uma nova será necessario realizar o cancelamento da solicitação errada e, somente apos isso, deverá ser efeituada uma nova solicitação;

11. O sistema deve solicitar, em todas as solicitações qual é o caixa em que o usuario solicitante está;

12. O cliente pede para que na tela de aprovações seja exibido um resumo da solicitação com os dados:
    - nome do solicitante;
    - Tipo de solicitação;
    - Motivo da solicitação (Se aplicável);

13. Quando o usuario aprovador for aprovar, antes de ser enviado uma notificação de que foi aprovado deve ser verificado se a solicitação ainda está ativa, caso nao esteja informe o usuario que aquela solicitação foi cancelada ou expirada;

14. A partir da observação do sistema atual do pdv da loja, observei que para permitir a entrada de texto fora do teclado (atualmente a loja usa um programavel tec65) é obrigatorio a adição de um underline "\_" no incio do texto;

15. O mapa das teclas para realização de cada ação é a seguinte:

    TECLA / AÇÃO

    C / Cancelamento de venda
    E / Cancela item
    R / retirada

16. O ip das maquinas são

NUMERO DO PDV / IP

101 / 192.168.20.101
102 / 192.168.20.102

## Requisitos Funcionais (RF)

- **RF01 - Interface Web de Solicitação e Aprovação:** O sistema deve possuir uma interface web responsiva para a criação de solicitações por operadores e avaliação por fiscais/gerentes.
- **RF02 - Tipagem e Justificativa:** Permitir a criação de requisições categorizadas (Cancelamento de Venda, Cancela Item, Desconto, Retirada, Suprimento, Crédito Rotativo) com exigência de dados complementares predefinidos conforme o tipo.
- **RF03 - Exigência de Credenciais:** Exigir a validação de matrícula e senha do usuário em ações críticas (enviar e aprovar), independentemente da sessão já estar logada.
- **RF04 - Controle de Validade:** Cada requisição deve ter validade de 5 minutos, sendo alterada para o status "Expirada" automaticamente após esse prazo.
- **RF05 - Atualização em Tempo Real:** Fiscais devem ser notificados imediatamente sobre novas requisições, e operadores devem ver o status e o tempo restante sem recarregar a página.
- **RF06 - Autorização Baseada em Regras (ACL):** Fiscais visualizam e aprovam apenas as requisições para as quais possuem privilégios configurados.
- **RF07 - Regra de Exclusividade:** Bloquear novas requisições se houver uma em aberto para o mesmo solicitante ou PDV, exigindo o cancelamento da requisição travada antes de abrir uma nova.
- **RF08 - Identificação de Origem:** Registrar e exigir o número do PDV de origem (ex: 101, 102) no ato da solicitação.
- **RF09 - Painel de Auditoria e Filtros:** Permitir filtros por status (Em aberto, Cancelada, Aprovada, Expirada) na listagem, com padrão "Em aberto", exibindo resumo com Nome, Tipo e Motivo.
- **RF10 - Validação de Concorrência:** O backend deve revalidar se a solicitação continua ativa (não cancelada/expirada) no exato instante da aprovação, antes de disparar a instrução para o PDV.
- **RF11 - Aprovação Automática Parametrizável:** Possuir módulo de configuração de horários e dias para aprovação automática, utilizando um usuário de sistema fictício para não comprometer a auditoria humana.
- **RF12 - Injeção de Eventos (Client C++):** O executável local deve escutar o servidor, inserir o caractere sublinhado ("\_"), simular as teclas mapeadas (C, E, R) e injetar as credenciais no PDV.

---

## Requisitos Não Funcionais (RNF)

- **RNF01 - Stack Tecnológico:** O projeto será dividido em Backend Java (API/WebSockets), Frontend em JavaScript e Agente Local em C++ rodando em background nos PDVs.
- **RNF02 - Comunicação Bidirecional:** Utilizar WebSockets ou Sockets TCP/gRPC para garantir a atualização de status na web e o envio imediato de comandos ao agente C++.
- **RNF03 - Segurança em Trânsito:** Criptografar obrigatoriamente a comunicação interna (TLS ou chave assimétrica) para proteger o tráfego de matrículas e senhas na rede local.
- **RNF04 - Controle de Foco do Sistema Operacional:** O agente C++ deve garantir, via API nativa do SO, que a janela correta do PDV está em foco e ativa antes de iniciar a injeção de teclas.
- **RNF05 - Resiliência do Agente:** O executável em C++ deve rodar como um serviço de sistema, com política de reinício automático em caso de falhas ou reinicialização da máquina.

## Estrutura das pastas

```text
/pdv-remote-approval
├── /backend-java               # API REST, WebSockets e Regras de Negócio
│   ├── /src
│   │   ├── /main/java/com/mercado/pdv
│   │   │   ├── /controllers    # Endpoints REST (recebem as requisições web)
│   │   │   ├── /websockets     # Gerenciamento de conexões em tempo real
│   │   │   ├── /services       # Lógica de negócio (validações, regras)
│   │   │   ├── /models         # Entidades do BD (Solicitacao, Usuario)
│   │   │   └── /repositories   # Comunicação com o banco de dados
│   │   └── /resources
│   │       └── application.properties # Configurações de BD e portas
│   └── pom.xml                 # Dependências Java
│
├── /frontend-js                # Interface Web para Operadores e Fiscais
│   ├── /public                 # Ícones, index.html estático
│   ├── /src
│   │   ├── /components         # Botões reutilizáveis, modais, cards
│   │   ├── /pages              # Telas inteiras (Login, PainelOperador)
│   │   ├── /services           # Chamadas para o backend Java
│   │   ├── /hooks              # Lógicas customizadas (ex: timer)
│   │   └── App.js              # Ponto de entrada das rotas da aplicação
│   └── package.json            # Dependências do frontend JavaScript
│
├── /agent-cpp                  # Executável que roda fisicamente no PDV
│   ├── /include                # Arquivos de cabeçalho (.h / .hpp)
│   │   ├── keyboard_injector.h # API do SO para controle de teclado
│   │   ├── socket_client.h     # Comunicação com o servidor Java
│   │   └── window_manager.h    # Garantia de foco na tela do PDV
│   ├── /src                    # Arquivos de implementação (.cpp)
│   │   ├── main.cpp            # Loop principal de execução do serviço
│   │   ├── keyboard_injector.cpp
│   │   ├── socket_client.cpp
│   │   └── window_manager.cpp
│   └── CMakeLists.txt          # Configuração de compilação do C++
│
├── README.md                   # Documentação geral do projeto
└── .gitignore                  # Arquivo para ignorar dependências
```
