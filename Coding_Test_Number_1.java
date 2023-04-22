package com.myspring.pro28;
class Category {
    String id;
    String name;
    List<Category> children; // 하위 카테고리

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Category child) {
        children.add(child);
    }

    // 카테고리 식별자로 검색
    public Category findCategoryById(String id) {
        if (this.id.equals(id)) {
            return this;
        }
        for (Category child : children) {
            Category result = child.findCategoryById(id);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    // 카테고리명으로 검색
    public List<Category> findCategoryByName(String name) {
        List<Category> result = new ArrayList<>();
        if (this.name.equals(name)) {
            result.add(this);
        }
        for (Category child : children) {
            result.addAll(child.findCategoryByName(name));
        }
        return result;
    }

    // Json text 로 변환
    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": \"" + id + "\",");
        sb.append("\"name\": \"" + name + "\",");
        sb.append("\"children\": [");
        for (Category child : children) {
            sb.append(child.toJson() + ",");
        }
        if (!children.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // 마지막에 추가되는 쉼표 제거
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}

class CategoryTree {
    Map<String, Category> categoryMap;

    public CategoryTree() {
        this.categoryMap = new HashMap<>();
    }

    // 카테고리 추가
    public void addCategory(String id, String name, String parent_id) {
        Category category = new Category(id, name);
        if (parent_id != null) {
            Category parent = categoryMap.get(parent_id);
            parent.addChild(category);
        }
        categoryMap.put(id, category);
    }

    // 카테고리 식별자로 검색
    public Category findCategoryById(String id) {
        return categoryMap.get(id);
    }

    // 카테고리명으로 검색
    public List<Category> findCategoryByName(String name) {
        List<Category> result = new ArrayList<>();
        for (Category category : categoryMap.values()) {
            result.addAll(category.findCategoryByName(name));
        }
        return result;
    }

    // Json text 로 변환
    public String toJson() {
        List<Category> rootCategories = new ArrayList<>();
        for (Category category : categoryMap.values()) {
            if (categoryMap.get(category.id) == null) {
                rootCategories.add(category);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Category category : rootCategories) {
            sb.append(category.toJson() + ",");
        }
        if (!rootCategories.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // 마지막에 추가되는 쉼표 제거
        }
        sb.append("]");
        return sb.toString();
    }
}