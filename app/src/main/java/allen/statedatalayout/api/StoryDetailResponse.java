package allen.statedatalayout.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import allen.statedatalayout.widget.GetInfoData;

/**
 * Created by Admin on 9/9/2016.
 */
public class StoryDetailResponse extends BaseResponse implements Parcelable, GetInfoData {
    @SerializedName("data")
    private StoryDetail storyDetail;

    public StoryDetail getStoryDetail() {
        return storyDetail;
    }

    @Override
    public boolean hasData() {
        if (storyDetail != null && !TextUtils.isEmpty(storyDetail.getStory_name())) return true;
        return false;
    }

    public static class StoryDetail implements Parcelable {
        @SerializedName("story_id")
        private String story_id;
        @SerializedName("story_name")
        private String story_name;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("description")
        private String description;
        @SerializedName("chapters")
        private HashMap<String, ChapterInfo> listChap;

        public String getStory_id() {
            return story_id;
        }

        public String getStory_name() {
            return story_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getDescription() {
            return description;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.story_id);
            dest.writeString(this.story_name);
            dest.writeString(this.avatar);
            dest.writeString(this.description);
//            dest.writeSerializable(this.listChap);
        }

        public StoryDetail() {
        }

        protected StoryDetail(Parcel in) {
            this.story_id = in.readString();
            this.story_name = in.readString();
            this.avatar = in.readString();
            this.description = in.readString();
//            this.listChap = (HashMap<String, ChapterInfo>) in.readSerializable();
        }

        public static final Creator<StoryDetail> CREATOR = new Creator<StoryDetail>() {
            @Override
            public StoryDetail createFromParcel(Parcel source) {
                return new StoryDetail(source);
            }

            @Override
            public StoryDetail[] newArray(int size) {
                return new StoryDetail[size];
            }
        };
    }

    public static class ChapterInfo implements Parcelable {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
        }

        public ChapterInfo() {
        }

        protected ChapterInfo(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
        }

        public static final Creator<ChapterInfo> CREATOR = new Creator<ChapterInfo>() {
            @Override
            public ChapterInfo createFromParcel(Parcel source) {
                return new ChapterInfo(source);
            }

            @Override
            public ChapterInfo[] newArray(int size) {
                return new ChapterInfo[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.storyDetail, flags);
    }

    public StoryDetailResponse() {
    }

    protected StoryDetailResponse(Parcel in) {
        this.storyDetail = in.readParcelable(StoryDetail.class.getClassLoader());
    }

    public static final Creator<StoryDetailResponse> CREATOR = new Creator<StoryDetailResponse>() {
        @Override
        public StoryDetailResponse createFromParcel(Parcel source) {
            return new StoryDetailResponse(source);
        }

        @Override
        public StoryDetailResponse[] newArray(int size) {
            return new StoryDetailResponse[size];
        }
    };

    @Override
    public String toString() {
        return storyDetail.getStory_name();
    }
}
