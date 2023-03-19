package com.tutorial.crud.controller;


import com.tutorial.crud.dto.Mensagem;
import com.tutorial.crud.dto.ProdutoDto;
import com.tutorial.crud.entity.Produto;
import com.tutorial.crud.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

   @GetMapping("/lista")
    public ResponseEntity<List<Produto>> List(){
       List<Produto> list = produtoService.list();
       return new ResponseEntity(list, HttpStatus.OK);
   }

   @ApiIgnore
   @GetMapping("/detail/{id}")
    public ResponseEntity<Produto> getById(@PathVariable("id") int id){
       if(!produtoService.existById(id))
           return new ResponseEntity(new Mensagem("não existe"), HttpStatus.NOT_FOUND);
       Produto produto = produtoService.getOne(id).get();
       return new ResponseEntity(produto, HttpStatus.OK);
   }
   @ApiIgnore
   @GetMapping("/detailname/{nome}")
       public ResponseEntity<Produto> getByNome(@PathVariable ("nome") String nome){
       if(!produtoService.existsByNome(nome));
       return  new ResponseEntity(new Mensagem("Nao existe"), HttpStatus.NOT_FOUND);
        Produto produto = produtoService.getByNome(nome).get();
        return  new ResponseEntity(produto, HttpStatus.OK);
   }
   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("/create")
    public ResponseEntity<?> create (@ResponseBody ProdutoDto produtoDto){
       if(StringUtils.isBlank(produtoDto.getNome()))
           return new ResponseEntity(new Mensagem("O nome é obrigatório"), HttpStatus.BAD_REQUEST);
           i(produtoDto.getPreco() == null || produtoDto.getPreco < 0)
           return new ResponseEntity(new Mensagem ("O preço deve ser maior que zero (0)"), HttpStatus.BAD_REQUEST);
           if(produtoService.exissByNome(produtoDto.getNome()))
               return new ResponseEntity(new Mensagem ("Esse nome já não existe"), HttpStatus.BAD_REQUEST);
           Produto produto = new Produto(produtoDto.getNome(), produtoDto.getPreco());
   }

}
