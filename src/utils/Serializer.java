package utils;

public interface Serializer {
   void save() throws Exception;
   void load() throws Exception;
   String fileName();
}
