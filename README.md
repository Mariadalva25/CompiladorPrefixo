Compilador de Expressões Infixas para Prefixas
Descrição

Este projeto foi desenvolvido para a disciplina de Compiladores e tem como objetivo implementar um compilador simplificado capaz de:

Realizar análise léxica (Scanner);
Realizar análise sintática preditiva utilizando Descendência Recursiva;
Aplicar Tradução Dirigida pela Sintaxe (TDS);
Converter expressões matemáticas da notação infixa para prefixa;
Respeitar as regras de precedência e associatividade dos operadores;
Tratar operadores unários.

Objetivos

O projeto tem como finalidade demonstrar na prática os conceitos estudados na disciplina de Compiladores, incluindo:

Construção de analisadores léxicos;
Construção de analisadores sintáticos LL(1);
Remoção de recursão à esquerda;
Tradução dirigida pela sintaxe;
Geração de código intermediário na forma de expressões prefixas.

Funcionalidades

O compilador reconhece:

Identificadores
A
B
variavel
x1

Números

10
25
3.14

Operadores

| Operador | Descrição                |
| -------- | ------------------------ |
| +        | Soma                     |
| -        | Subtração                |
| *        | Multiplicação            |
| /        | Divisão                  |
| %        | Resto da divisão         |
| ^        | Potenciação              |
| +u       | Operador unário positivo |
| -u       | Operador unário negativo |


Delimitadores

(
)

Estrutura do Projeto

CompiladorPrefixo/
│
└── src/
    │
    ├── Main.java
    │
    ├── lexer/
    │   ├── Lexer.java
    │   ├── Token.java
    │   └── TokenType.java
    │
    └── parser/
        └── Parser.java

Componentes
TokenType.java

Define todos os tipos de tokens reconhecidos pelo compilador.

Exemplos:

ID
NUMBER
PLUS
MINUS
MULT
DIV
MOD
POW
LPAREN
RPAREN
EOF

Token.java

Representa um token gerado pelo analisador léxico.

Exemplo:

ID -> A
PLUS -> +
NUMBER -> 10

Lexer.java

Responsável pela análise léxica.

Funções:

Ler a entrada caractere por caractere;
Identificar identificadores;
Identificar números;
Identificar operadores;
Gerar a lista de tokens.

Exemplo:

Entrada:

A + B

Saída:

ID -> A
PLUS -> +
ID -> B
EOF -> EOF

Parser.java

Responsável pela análise sintática e pela tradução para notação prefixa.

Implementa o método de:

Descendência Recursiva

Realiza:

Verificação sintática;
Controle de precedência;
Conversão para prefixa;
Tratamento de operadores unários.

Main.java

Classe principal responsável por:

Receber a expressão digitada pelo usuário;
Executar o Lexer;
Exibir os tokens;
Executar o Parser;
Exibir a expressão prefixa gerada.

Gramática Utilizada

Após a remoção da recursão à esquerda, a gramática utilizada foi:

Expr     → Term Expr'

Expr'    → (+|-) Term Expr'
         | ε

Term     → Power Term'

Term'    → (*|/|%) Power Term'
         | ε

Power    → Unary Power'

Power'   → ^ Unary Power'
         | ε

Unary    → + Unary
         | - Unary
         | Primary

Primary  → ID
         | NUMBER
         | ( Expr )

Tradução Dirigida pela Sintaxe (TDS)

A tradução é realizada utilizando atributos sintetizados.

Cada método retorna uma string contendo a representação prefixa da subexpressão analisada.

Exemplo:

Entrada:

A + B

Saída:

+ A B

Exemplos de Execução
Exemplo 1

Entrada:

A * B + C * D

Saída:

+ * A B * C D
 
Exemplo 2

Entrada:

(A + B) / (C + D)

Saída:

/ + A B + C D

Exemplo 3

Entrada:

A + B + C ^ 2 + D

Saída:

+ + + A B ^ C 2 D
   
Exemplo 4

Entrada:

-(A + B)

Saída:

-u + A B

Como Executar
1. Compilar o projeto

Dentro da pasta src, execute:

javac Main.java lexer/*.java parser/*.java

2. Executar

java Main

3. Informar uma expressão

Exemplo:

Digite uma expressão:
(A+B)/(C+D)

Resultado:

TOKENS:
LPAREN -> (
ID -> A
PLUS -> +
ID -> B
RPAREN -> )
DIV -> /
LPAREN -> (
ID -> C
PLUS -> +
ID -> D
RPAREN -> )
EOF -> EOF

Expressão Prefixa:
/ + A B + C D

Tecnologias Utilizadas
Java
Programação Orientada a Objetos (POO)
Análise Léxica
Análise Sintática Preditiva
Descendência Recursiva
Tradução Dirigida pela Sintaxe (TDS)

Autores

Anna Priscilla Moreira de Figueiredo, Maria Dalva de Oliveira Gomes e Thyago Oliveira Diniz

Projeto desenvolvido para a disciplina de Compiladores, com o objetivo de implementar um compilador simplificado para conversão de expressões infixas em expressões prefixas utilizando técnicas clássicas de análise léxica e sintática.
