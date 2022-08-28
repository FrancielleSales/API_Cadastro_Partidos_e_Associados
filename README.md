# API para cadastro de partidos e seus associados

<p align="center">
<img src = "https://user-images.githubusercontent.com/100395899/171477325-bc2f0b24-c7f6-4d97-9bbb-c39974b5a163.png">
</p>

## Endpoints

### Partido

- **Post** - /partidos

- **Get** - /partidos 

  *Possui a opção de filtrar os partidos de acordo com sua ideologia).*
  
- **Get** - /partidos/{id}

- **Get** - /partidos/{id}/associados 

  *Permite listar todos os associados do partido.*
  
- **Put** - /partidos/{id}

- **Delete** - /partidos/{id}

### Associado

- **Post** - /associados

- **Post** - /associados/partidos 

  *Vincula um associado a um partido, com o body: {“idAssociado”:10, “idPartido”: 10}.*
  
- **Get** - /associados 

  *Possui as opção de filtrar os associados de acordo com o cargo político e de ordená-los por nome.*
  
- **Get** - /associados/{id}

- **Put** - /associados/{id}

- **Delete** - /associados/{id}

- **Delete** - /associados/{d}/partidos/{id} 

  *Remove um determinado associado do partido.*
