package helpers;
import java.util.HashMap;
import java.util.Map;

public class QueryParamsBuilder {
    private Map<String, String> queryParams;

    public QueryParamsBuilder() {
        queryParams = new HashMap<>();
    }

    public void addQueryParam(String paramName, String paramValue) {
        queryParams.put(paramName, paramValue);
    }

    public void addQueryParam(ProductFilters filters){
        for(ProductFilters.Filter filter:filters.getFilters()){
            if(filter.addTOQueryParams)
                addQueryParam(filter.getParamName(),filter.getParamValue());
        }
    }
    public void addLimitParam(int limit) {
        addQueryParam("_limit", limit+"");
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!first) {
                result.append("&");
            }
            result.append(entry.getKey()).append("=").append(entry.getValue());
            first = false;
        }

        return result.toString();
    }

}
