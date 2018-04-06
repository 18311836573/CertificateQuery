package org.vatalu.model.response;

import java.util.List;

public class QueryResponse<T> extends CommonResponse {
    private Integer total;
    private List<T> rows;

    public QueryResponse(Boolean result) {
        super(result);
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public QueryResponse(Boolean result, Integer total, List<T> rows) {
        super(result);
        this.total = total;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "QueryResponse{" +
                "total=" + total +
                ", rows=" + rows +
                ", result=" + result +
                '}';
    }
}
