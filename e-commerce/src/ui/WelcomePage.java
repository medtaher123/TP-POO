package ui;

import Managers.PageManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;


public class WelcomePage extends Page {

    @Override
    protected String getTitle() {
        return "Welcome " + AuthenticationSystem.getActiveUser().getFirstName();
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.CUSTOMER | AccessLevel.ADMIN;
    }

    public void printContent() {
        System.out.println("what can I do for you today\n");


        Action[] userSpecificActions=null;
        switch(AuthenticationSystem.getActiveUser().getType()) {
            case CUSTOMER:
                userSpecificActions = customerActions;
                break;
            case ADMIN:
                userSpecificActions = adminActions;
                break;
            default:
                throw new RuntimeException("Unknown user type");
        }

        //Action[] actions = concatenateArrays(generalActions,userSpecificActions);
        new ActionMenu(userSpecificActions,ActionMenu.LOGOUT_ACTION).execute();

    }

    public static Action[] concatenateArrays(Action[]... arrayOfArrays) {
        int totalLength = 0;
        for (Action[] arr : arrayOfArrays) {
            totalLength += arr.length;
        }

        Action[] result = new Action[totalLength];
        int currentIndex = 0;
        for (Action[] arr : arrayOfArrays) {
            System.arraycopy(arr, 0, result, currentIndex, arr.length);
            currentIndex += arr.length;
        }

        return result;
    }


    Action[] customerActions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Our Products";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ProductListPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Search Product";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ProductListPage(ConsoleHelper.input("search")));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Manage Account";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ManageAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Blank action";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new NotFoundPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Blank action";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new NotFoundPage());
                    return null;
                }
            }
    };
    Action[] adminActions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "All Products";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ProductListPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Search Product";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ProductListPage(ConsoleHelper.input("search")));
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Manage Account";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new ManageAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "registerStockArrival";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new RegisterStockArrivalPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Blank action";
                }

                @Override
                public Object execute() {
                    PageManager.callPage(new NotFoundPage());
                    return null;
                }
            }
    };


}
