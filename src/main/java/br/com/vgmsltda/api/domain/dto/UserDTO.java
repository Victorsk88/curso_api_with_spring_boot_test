package br.com.vgmsltda.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;

//    @JsonIgnore
    {/*ao inves de usarmos JsonIgnore para ocultar uma informação do endpoint
    podemos utilizar o JsonProperty para acessa-lo atraves do access, entao colocamos uma condição nele
    por exemplo no caso do access abaixo, está dizendo que ele só terá acesso quando estiver gravando
    no banco e nao é mostrado no Json. Se usassemos @JsonIgnore a informação nao seria gravada no banco
    seria utilizada somente para ocultar mas nao conseguiriamos grava-la*/}
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
