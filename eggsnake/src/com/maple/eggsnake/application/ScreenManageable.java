/* 
 * Description	: Game Manager interface
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.application;

import com.maple.eggsnake.screen.ScreenBase;

public interface ScreenManageable {

	void enterScreenFinished(ScreenBase Screen);

	void exitScreenFinished(ScreenBase Screen);

	void requestNavigate(ScreenBase Screen);
}
