/* 
 * Description	: GameEntry
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.android;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.maple.eggsnake.GameListener;

public class Game extends AndroidApplication{
	public void onCreate (android.os.Bundle savedInstance){
		super.onCreate(savedInstance);
		initialize(new GameListener(),false);
	}
}
