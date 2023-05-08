package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class CartController {

    List<CartDto> CartDtoList = new ArrayList<CartDto>(){
        {
            add(new CartDto(1L, "kurtka zimowa", "Pellentesque tempus interdum quam ut rhoncus. Donec...", new BigDecimal(100), 1L));
            add(new CartDto(2L, "płaszcz", "Pellentesque tempus interdum quam ut rhoncus. Donec...", new BigDecimal(150), 1L));
            add(new CartDto(8L,"krawat", "Pellentesque tempus interdum quam ut rhoncus. Donec...", new BigDecimal(50), 2L));

        }
    };

    @GetMapping
    public ResponseEntity<List<CartDto>> getCart(){
        return ResponseEntity.ok(CartDtoList);
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long cartId){
        for (CartDto cart : CartDtoList) {
            if (cart.getId().equals(cartId)){
                return ResponseEntity.ok(cart);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        for (CartDto cart : CartDtoList) {
            if (cart.getId().equals(cartId)){
                CartDtoList.remove(cart);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="{cartId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> updateCart(@PathVariable Long cartId, @RequestBody CartDto cartDto) {
        for (CartDto cart : CartDtoList) {
            if (cart.getId().equals(cartId)) {
                CartDtoList.remove(cart);
                CartDtoList.add(cartDto);
                return ResponseEntity.ok(cartDto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCart(@RequestBody CartDto cartDto) {
        CartDtoList.add(cartDto);
        return ResponseEntity.ok().build();
    }
}