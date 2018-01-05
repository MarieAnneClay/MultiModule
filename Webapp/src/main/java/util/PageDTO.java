package util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

public class PageDTO<T> {
  int pageIndex;
  int pageSize;
  String search;
  SortDTO sort;
  int totalPages;
  long totalElements;
  List<T> elements;
  
  public PageDTO(Page<T> page, String search, String column, Direction sort) {
    this.pageIndex = page.getNumber();
    this.pageSize = page.getSize();
    this.search = search;
    this.sort = new SortDTO(column, sort);
    this.totalPages = page.getTotalPages();
    this.totalElements = page.getTotalElements();
    this.elements = page.getContent();

  }
  
  public int getPageIndex() {
    return pageIndex;
  }
  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public String getSearch() {
    return search;
  }
  public void setSearch(String search) {
    this.search = search;
  }
  public SortDTO getSort() {
    return sort;
  }
  public void setSort(SortDTO sort) {
    this.sort = sort;
  }
  public int getTotalPages() {
    return totalPages;
  }
  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }
  public long getTotalElements() {
    return totalElements;
  }
  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }
  public List<T> getElements() {
    return elements;
  }
  public void setElements(List<T> elements) {
    this.elements = elements;
  }

}
