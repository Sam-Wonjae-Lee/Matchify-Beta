# CSC207 Project Matchify Group 52

## **Project Goal**
Our team's goal is to develop a program that matches Spotify users based on their music taste. This is achieved by analyzing songs from the users' playlists and determine their music taste. Based on the similarity of the music tastes, we will determine to match the users. Our implementation involves using the Spotify Web API and following Clean Architecture and Solid Principles.

Members:\
Wonjae Lee - Sam-Wonjae-Lee (Wonjae Lee)\
Andy Ren - 1172097\
David Li - dvdli123 (Jiaming Li)\
Michael Pan - Yaymichael\
Frank Peng - Graves451 (Frunk Peng)

[Google Doc Notes](https://docs.google.com/document/d/1fccZgwsFML7ln8LYjmQiHKzzh7x_m6ANvomALBYI5uc/edit)

[Google Slides Presentation](https://docs.google.com/presentation/d/1cYuKYeAxn5X2vGnBf689F8s1d6cob18szrKQlGUUwNg/edit?usp=sharing)

## **Spotify Web API**
The Spotify Web API provides a variety of ways for developers to interact with Spotify's streaming service, like receiving information about users, playlists, and artists. Our group uses the API to retrieve the music taste of users using their Spotify user ID. \
This is process for our implementation:
Retrieve user ID -> Get user's playlist IDs -> Get artist IDs from playlist ID -> Get genres from artist ID \
We use the genres from each user to determine if they match based on the similarities.\

For more information about the Spotify API, please visit [source](https://developer.spotify.com/documentation/web-api) and [Java Wrapper source](https://github.com/spotify-web-api-java/spotify-web-api-java)

---

## **Credits**

### App
- HomePageFactory, MatchFactory by **Andy Ren**
- LoginUseCaseFactory by **Wonjae Lee**
- Main by **Michael Pan**

### Data Access Object
- FileUserDAO by **Frank Peng**
- InMemoryUserDAO by **David Li**
- SpotifyApiCallAccessTokenDAO, SpotifyApiCallArtistGenresDAO, SpotifyApiCallGetInfoDAO, SpotifyApiCallPlaylistItemsDAO, SpotifyApiCallUserPlaylistDAO, SpotifyApiCallUserProfileDAO by **Wonjae Lee**

### Entity
- CommonUserFactory, UserFactory, User by **David Li**
- Inbox, FriendsList, Playlist by **Frank Peng**
- CommonUser by **Andy Ren**

### Interface Adapter
Accept Invite:
- AcceptController, AcceptPresenter by **Michael Pan**
Decline Invite:
- DeclineController, DeclinePresenter by **Michael Pan**
Home Page:
- HomePageController, HomePagePresenter, HomePageState, HomePageViewModel by **Andy Ren**
Inbox:
- InboxState, InboxViewModel by **David Li**
Login:
- LoginController, LoginPresenter, LoginState, LoginViewModel by **Wonjae Lee**
Match:
- MatchController, MatchPresenter, MatchState, MatchViewModel by **Andy Ren**
Open Inbox:
- OpenInboxController, OpenInboxPresenter by **David Li**
Send Invite:
- SendInviteController, SendInvitePresenter by **David Li**
View:
- ViewManagerModel by **David Li**
- ViewModel by **Andy Ren**

### Use Case
Accept Invite:
- AcceptInputBoundary, AcceptInputData, AcceptInteractor, AcceptOutputBoundary, AcceptOutputData, AcceptUserDataAccessInterface by **Michael Pan**
Decline Invite:
- DeclineInputBoundary, DeclineInputData, DeclineInteractor, DeclineOutputBoundary, DeclineOutputData, DeclineUserDataAccessInterface by **Michael Pan**
Home Page:
- HomePageInputBoundary, HomePageInputData, HomePageInteractor, HomePageOutputBoundary, HomePageOutputData by **Andy Ren**
Login:
- LoginInputBoundary, LoginInputData, LoginInteractor, LoginOutputBoundary, LoginOutputData, LoginUserDataAccessInterface by **Wonjae Lee**
Match:
- MatchInputBoundary, MatchInputData, MatchInteractor, MatchOutputBoundary, MatchOutputData, MatchUserDataAccessInterface by **Andy Ren**
Open Inbox:
- OpenInboxInputBoundary, OpenInboxInputData, OpenInboxInteractor, OpenInboxOutputBoundary, OpenInboxOutputData, OpenInboxUserDataAccessInterface by **David Li**
Send Invite:
- SendInviteInputBoundary, SendInviteInputData, SendInviteInteractor, SendInviteOutputBoundary, SendInviteOutputData, SendInviteUserDataAccessInterface by **David Li**

### View
- HomePageView, MatchView, ProfilePic by **Andy Ren**
- InboxView by **Michael Pan**
- LabelTextPanel, LoginView by **Wonjae Lee**
- ViewManager by **David Li**

