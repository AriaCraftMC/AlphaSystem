//===============================================//
//重新编译已禁用.请用JDK运行Recaf//
//===============================================//

// Decompiled with: FernFlower
// Class Version: 8
package cc.nuym.alphasystem.generic;

import com.google.gson.Gson;

import java.io.*;

public class Storage {
    Gson gson;

    public Storage(Gson var1) {
        this.gson = var1;
    }

    public <T> T loadNotNull(T var1, Class<T> var2, File var3) throws FileNotFoundException {
        if (!var3.exists()) {
            String var10 = this.gson.toJson(var2);
            PrintWriter var5 = new PrintWriter(var3);

            try {
                var5.println(var10);
            } catch (Throwable var9) {
                try {
                    var5.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            var5.close();
            return (T)var1;
        } else {
            Object var4 = this.gson.fromJson(new FileReader(var3), var2);
            return (T)(var4 == null ? var1 : var4);
        }
    }

    public void save(Object var1, File var2) throws IOException {
        FileWriter var3 = new FileWriter(var2);
        this.gson.toJson(var1, var3);
        var3.flush();
        var3.close();
    }
}
 