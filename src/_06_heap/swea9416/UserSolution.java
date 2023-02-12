package _06_heap.swea9416;

class UserSolution {
    
    private final boolean[][] follows = new boolean[1001][1001];
    private final Post[] posts = new Post[100001];
    
    private int postCount;
    
    void init(int N) {
        for (int uID1 = 1; uID1 <= N; uID1++) {
            for (int uID2 = 1; uID2 <= N; uID2++) {
                follows[uID1][uID2] = false;
            }
            follows[uID1][uID1] = true;
        }
        
        postCount = 0;
    }
    
    void follow(int uID1, int uID2, int timestamp) {
        follows[uID1][uID2] = true;
    }
    
    void makePost(int uID, int pID, int timestamp) {
        posts[++postCount] = new Post(pID, uID, timestamp);
    }
    
    void like(int pID, int timestamp) {
        posts[pID].like++;
    }
    
    void getFeed(int uID, int timestamp, int[] pIDList) {
        int listCount = 0;
        for (int postIndex = postCount; postIndex > 0; postIndex--) {
            Post post = posts[postIndex];
            
            if (!follows[uID][post.uID]) {
                continue;
            }
            
            int postTime = timestamp - post.timestamp;
            if (postTime > 1000 && listCount == 10) {
                break;
            }
            
            for (int listIndex = 0; listIndex < 10; listIndex++) {
                if (listIndex == listCount) {
                    pIDList[listIndex] = post.pID;
                    listCount++;
                    break;
                }
                
                Post prev = posts[pIDList[listIndex]];
                if (timestamp - prev.timestamp <= 1000 && postTime <= 1000) {
                    if (post.like > prev.like) {
                        for (int i = 9; i > listIndex; i--) {
                            if (pIDList[i - 1] != 0) {
                                pIDList[i] = pIDList[i - 1];
                            }
                        }
                        pIDList[listIndex] = post.pID;
                        listCount++;
                        break;
                    }
                }
            }
        }
    }
}

class Post {
    
    int pID, uID, timestamp, like;
    
    Post(int pID, int uID, int timestamp) {
        this.pID = pID;
        this.uID = uID;
        this.timestamp = timestamp;
        this.like = 0;
    }
}
