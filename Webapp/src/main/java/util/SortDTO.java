package util;

import org.springframework.data.domain.Sort.Direction;

public class SortDTO {
  String column;
  Direction orientation;
  
  public SortDTO(String column, Direction orientation) {
    super();
    this.column = column;
    this.orientation = orientation;
  }
  
  public String getColumn() {
    return column;
  }
  public void setColumn(String column) {
    this.column = column;
  }
  public Direction getOrientation() {
    return orientation;
  }
  public void setOrientation(Direction orientation) {
    this.orientation = orientation;
  }

}
