using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class HudController : MonoBehaviour {

	public Fighter fighter1;
	public Fighter fighter2;
	public Text fighterLeftName;
	public Text fighterRigthName;
	public Text countdownTime;
	public float battleTime = 99;

	bool battleEnd;
	float lastUpdate;
	bool startCountDown;
	public static HudController instance;
	AudioSource audio;
	public AudioClip celebration;

	void Awake(){
		instance = this;
	}

	void Start () {
		audio = GetComponent<AudioSource> ();
		fighterLeftName.text = fighter1.fighterName;
		fighterRigthName.text = fighter2.fighterName;
		countdownTime.text = battleTime.ToString ();
	}

	void Update () {
		if (!battleEnd && startCountDown) {
			if(battleTime > 0 && Time.time -lastUpdate > 1){
				battleTime--;
				lastUpdate = Time.time;
				countdownTime.text = battleTime.ToString ();
				if (battleTime == 0) {
					battleEnd = true;
				}
				if (!battleEnd) {
					if (fighter1.health <= 0) {
						GameOver (false);
					} else if(fighter2.health<=0) {
						GameOver (true);
					}	
				}
			}
		}
	}

	void GameOver(bool player1Won){
		SoundManager.playSound (audio, celebration,1,true);
		battleEnd = true;
		if (player1Won) {
			BannerManager.instance.Player1Win ();
			fighter1.victory ();
		} else {
			BannerManager.instance.Player2Win ();
			fighter2.victory ();
		}
	}

	public void CountDownStart(){
		startCountDown = true;
		lastUpdate = Time.time;
	}
}
