package com.fernandocejas.java.samples.optional;

import com.fernandocejas.arrow.annotations.See;
import com.fernandocejas.arrow.optional.Optional;
import com.fernandocejas.arrow.strings.Strings;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@See(ref = "")
public class UseCaseScenario02 {

  private static final String JSON_FILE = "[{\n"
      + "  \"id\": 1,\n"
      + "  \"full_name\": \"Simon Hill\",\n"
      + "  \"nickname\": \"ironman\"\n"
      + "}, {\n"
      + "  \"id\": 2,\n"
      + "  \"full_name\": \"Peter Graham\"\n"
      + "}, {\n"
      + "  \"id\": 3,\n"
      + "  \"full_name\": \"Angelina Johnston\",\n"
      + "  \"nickname\": \"ange\"\n"
      + "}, {\n"
      + "  \"id\": 4,\n"
      + "  \"full_name\": \"Josh Hunt\"\n"
      + "}, {\n"
      + "  \"id\": 5,\n"
      + "  \"full_name\": \"Victor Wallace\"\n"
      + "}, {\n"
      + "  \"id\": 6,\n"
      + "  \"full_name\": \"Lorena Bishop\",\n"
      + "  \"nickname\": \"lore\"\n"
      + "}]";

  private final List<User> usersList;

  public UseCaseScenario02() {
    usersList = getUsers();
  }

  @NotNull private List<User> getUsers() {
    final Gson gson = new Gson();
    final User[] users = gson.fromJson(JSON_FILE, User[].class);
    return Arrays.asList(users);
  }

  @Override public String toString() {
    final StringBuilder builder = new StringBuilder();
    for (User user : usersList) {
      builder.append("\n---------------------------------\n");
      builder.append("UserId: ").append(user.id()).append("\n");
      builder.append("FullName: ").append(user.fullname()).append("\n");
      builder.append("NickName: ").append(user.nickname().isPresent() ? user.nickname().get() : Strings.EMPTY);
      builder.append("\n---------------------------------\n");
    }
    return builder.toString();
  }

  private static final class User {
    @SerializedName("id")
    private int userId;

    @SerializedName("full_name")
    private String fullname;

    @SerializedName("nickname")
    private String nickname;

    public int id() {
      return userId;
    }

    public String fullname() {
      return fullname;
    }

    public Optional<String> nickname() {
      return Optional.fromNullable(nickname);
    }
  }
}
