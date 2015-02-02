//import com.github.jasongoetz.jamarest.Jama;
//import com.github.jasongoetz.jamarest.domain.item.Item;
//import com.github.jasongoetz.jamarest.domain.item.Location;
//import com.github.jasongoetz.jamarest.domain.item.Parent;
//import com.github.jasongoetz.jamarest.domain.item.RequestItem;
//import com.github.jasongoetz.jamarest.domain.user.User;
//import com.github.jasongoetz.jamarest.domain.wrapper.PageResults;
//import com.github.jasongoetz.jamarest.exception.ApiException;
//import com.sun.jersey.api.client.ClientResponse;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//public class JamaRestClientTest {
//
//    private Jama jama;
//
//    private final String jamaURL = "";
//    private final String username = "";
//    private final String password = "";
//
//    private final int userId = 0;
//    private final int itemId = 0;
//    private final int projectId = 0;
//    private final int itemType = 0;
//
//    @Before
//    public void setupClient() {
//        jama = new Jama(jamaURL, username, password);
//    }
//
//    @Test
//    public void testGetUsers() {
//        PageResults<User> results = jama.users().getUsers(null, null);
//        for (User user : results.getResults()) {
//            System.out.println(user.getFirstName());
//        }
//    }
//
//    @Test
//    public void testGetUser() {
//        User user = jama.users().getUser(userId);
//        System.out.println(user.getFirstName());
//        Assert.assertEquals(userId, user.getId().intValue());
//    }
//
//    @Test
//    public void testGetItem() {
//        Item item = jama.items().getItem(itemId);
//        System.out.println(item.getDocumentKey());
//        Assert.assertEquals(itemId, item.getId().intValue());
//    }
//
//    @Test
//    public void testGetItems() {
//        PageResults<Item> results = jama.items().getItems(projectId, null, null);
//        for (Item item : results.getResults()) {
//            System.out.println(item.getDocumentKey());
//            Assert.assertEquals(projectId, item.getProject().intValue());
//        }
//    }
//
//    @Test
//    public void testGetItemsNoProject() {
//        try {
//            jama.items().getItems(null, null, null);
//        }
//        catch (ApiException e) {
//            Assert.assertEquals(ClientResponse.Status.BAD_REQUEST, e.getStatus());
//            return;
//        }
//        Assert.fail("Should have gotten bad request");
//    }
//
//    @Test
//    public void testCreateItem() {
//        RequestItem item = new RequestItem();
//        item.setProject(projectId);
//        item.setItemType(itemType);
//
//        Parent parent = new Parent();
//        parent.setItem(itemId);
//        Location location = new Location();
//        location.setParent(parent);
//        item.setLocation(location);
//
//        item.setFields(new HashMap<String, Object>());
//        item.getFields().put("name", "This is a test task");
//
//        Integer returnedItemId = jama.items().createItem(item);
//        Item returnedItem = jama.items().getItem(returnedItemId);
//        Assert.assertEquals(item.getItemType(), returnedItem.getItemType());
//        Assert.assertEquals(item.getProject(), returnedItem.getProject());
//        Assert.assertEquals(item.getFields().get("name"), returnedItem.getFields().get("name"));
//    }
//
//    @Test
//    public void testUpdateItem() {
//        Item item = jama.items().getItem(itemId);
//        String oldName = (String) item.getFields().get("name");
//        System.out.println("Old Name: " + oldName);
//
//        String newName = "New Tasks Set Name";
//        RequestItem requestItem = new RequestItem();
//        requestItem.setItemType(item.getItemType());
//        requestItem.setChildItemType(item.getChildItemType());
//        requestItem.setGlobalId(item.getGlobalId());
//        requestItem.setLocation(item.getLocation());
//        requestItem.setProject(item.getProject());
//        requestItem.setFields(item.getFields());
//        requestItem.getFields().put("name", newName);
//        jama.items().updateItem(itemId, requestItem);
//        Item returnedItem = jama.items().getItem(itemId);
//        System.out.println("New Name: " + returnedItem.getFields().get("name"));
//        Assert.assertEquals(newName, returnedItem.getFields().get("name"));
//
//        requestItem.getFields().put("name", oldName);
//        jama.items().updateItem(itemId, requestItem);
//        returnedItem = jama.items().getItem(itemId);
//        Assert.assertEquals(oldName, returnedItem.getFields().get("name"));
//    }
//
//}
