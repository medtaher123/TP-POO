package helpers;

import java.util.ArrayList;
import java.util.List;

public class ProductFilters {
    //TODO: add to doc: this class is used to handle filter on different attributes in the ProductListPage, but only used with title and category.
    // This structure makes the filter feature easily scalable, but in this the current situation, it's a little over engineered for just Category and title
    private List<Filter> filters;

    public ProductFilters(Filter... filters) {
        this.filters = new ArrayList<>();
        for(Filter filter: filters){
            if(filter!=null)
                this.filters.add(filter);
        }
    }

    public void addAttributeFilter(String attributeName, String attributeValue) {
        filters.add(new AttributeFilter(attributeName, attributeValue));
    }

    public void addSearchFilter(String titleSubstring) {
        filters.add(new SearchFilter(titleSubstring));
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Filter filter:filters) {
            if (!first) {
                result.append("\n y");
            }
            result.append(filter.toString());
            first = false;
        }

        return result.toString();
    }

    public static abstract class Filter {

        public abstract String toString();

        public abstract String getParamName();
        public abstract String getParamValue();
        public boolean addTOQueryParams=true;
    }

    public static class AttributeFilter extends Filter {
        private String attributeName;
        private String attributeValue;

        public AttributeFilter(String attributeName, String attributeValue) {
            this(attributeName, attributeValue, true);
        }

        public AttributeFilter(String attributeName, String attributeValue, boolean addToQueryParams) {
            this.attributeName = attributeName;
            this.attributeValue = attributeValue;
            this.addTOQueryParams = addToQueryParams;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        @Override
        public String toString() {
            return attributeName + ": " + attributeValue;
        }

        @Override
        public String getParamName() {
            return attributeName;
        }

        @Override
        public String getParamValue() {
            return attributeValue;
        }
    }

    public static class SearchFilter extends Filter {
        private String searchString;

        public SearchFilter(String searchString) {
            this.searchString = searchString;
        }

        public String getSearchString() {
            return searchString;
        }

        @Override
        public String toString() {
            return "Search: " + searchString;
        }

        @Override
        public String getParamName() {
            return "title_like";
        }

        @Override
        public String getParamValue() {
            return searchString;
        }
    }
}

