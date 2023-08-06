package com.sugadev.dietta.API;

import android.util.Base64;
import android.util.Log;

import com.auth0.android.jwt.JWT;

public class JwtUtils {
    public static String jwtGetRoles(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return null;
            }

            String base64EncodedBody = parts[1];
            byte[] bytes = Base64.decode(base64EncodedBody, Base64.URL_SAFE);
            String decodedPayload = new String(bytes);

            Log.d("Decoded Payload", decodedPayload);

            JWT jwt = new JWT(token);
            String roles = jwt.getClaim("roles").asString();

            Log.d("Decrypted Roles", roles);


            return roles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String jwtGetId(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return null;
            }

            String base64EncodedBody = parts[1];
            byte[] bytes = Base64.decode(base64EncodedBody, Base64.URL_SAFE);
            String decodedPayload = new String(bytes);

            Log.d("Decoded Payload", decodedPayload);

            JWT jwt = new JWT(token);
            String idUser = jwt.getClaim("sub").asString();

            String[] splitter = idUser.split(",");
            String id = splitter[0];

            Log.d("Decrypted sub", idUser);


            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
