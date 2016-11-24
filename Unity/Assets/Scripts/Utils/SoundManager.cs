using UnityEngine;

public class SoundManager {

	public static void playSound(AudioSource audio, AudioClip clip, 
		float volume = 1, bool loop = false){
		audio.Stop ();
		audio.clip = clip;
		audio.volume = volume;
		audio.loop = loop;
		audio.Play ();
	}
}
