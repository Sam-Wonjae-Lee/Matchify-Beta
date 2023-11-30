//package data_access;
//
//import se.michaelthelin.spotify.SpotifyApi;
//import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
//import se.michaelthelin.spotify.model_objects.specification.Artist;
//import se.michaelthelin.spotify.model_objects.specification.Paging;
//import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
//import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
//import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
//import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
//import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Scanner;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//public class SpotifyApiCallPlaylistItemsDataAccessObject {
//    private static final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
//    private static final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
//    private static final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter access token: ");
//        String accessToken = scanner.nextLine();
//
//        System.out.println("Enter user Id: ");
//        String playlistId = scanner.nextLine();
//
//        SpotifyApi spotifyApi = new SpotifyApi.Builder()
//                .setClientId(CLIENT_ID)
//                .setClientSecret(CLIENT_SECRET)
//                .setRedirectUri(URI.create(REDIRECT_URI))
//                .setAccessToken(accessToken)
//                .build();
//
//        // Get playlist tracks
//        try {
//            Paging<PlaylistTrack> playlistTracks = getPlaylistItems(spotifyApi, playlistId)
//        } catch () {
//
//        }
//
//
//    }
//
//    private static Paging<PlaylistTrack> getPlaylistItems(SpotifyApi spotifyApi, String playlistId)
//        throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException {
//
//        GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistId)
//                .limit(50)
//                .offset(0)
//                .build();
//
//        // Execute the request asynchronously
//        Future<Paging<PlaylistTrack>> pagingFuture = getPlaylistsItemsRequest.executeAsync();
//
//        // Wait for request to complete
//        return pagingFuture.get();
//
//    }
//
//    private static Artist getDetailedArtist(SpotifyApi spotifyApi, String artistId)
//            throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException {
//        // Create a request to get detailed information about an artist
//        GetArtistRequest request = spotifyApi.getArtist(artistId).build();
//
//        // Execute the request asynchronously
//        Future<Artist[]> artistsFuture = (Future<Artist[]>) request.executeAsync();
//
//        // Wait for the request to complete
//        Artist[] artists = artistsFuture.get();
//
//        // Return the first artist (there should be only one) or null if not found
//        return artists.length > 0 ? artists[0] : null;
//    }
//
//
//}
