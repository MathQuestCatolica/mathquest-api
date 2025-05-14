package com.mathquest.mathquest.feature.item.service;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> new ItemDTO(item.getId(), item.getName(), item.getType()))
                .toList();
    }

    public ItemDTO getItemById(Long id) {
        return itemRepository.findById(id)
                .map(item -> new ItemDTO(item.getId(), item.getName(), item.getType()))
                .orElse(null);
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setType(itemDTO.getType());
        item = itemRepository.save(item);
        return new ItemDTO(item.getId(), item.getName(), item.getType());
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setName(Objects.nonNull(itemDTO.getName()) ? itemDTO.getName() : item.getName());
            item.setType(Objects.nonNull(itemDTO.getType()) ? itemDTO.getType() : item.getType());
            item = itemRepository.save(item);
            return new ItemDTO(item.getId(), item.getName(), item.getType());
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item getItemByIdOrThrow(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));
    }
}
