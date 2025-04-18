package msproject.category.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    @PutMapping()
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if(categoryService.existCategory(category.getId())) {
            return ResponseEntity.ok(categoryService.updateCategory(category));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable int id) {
        if(categoryService.existCategory(id)) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
