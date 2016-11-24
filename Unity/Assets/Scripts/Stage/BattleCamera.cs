using UnityEngine;
using System.Collections;

public class BattleCamera : MonoBehaviour {

	public Transform leftPosition;
	public Transform rightPosition;
	public float maxDistance;

	void Update () {
		float distanceTotargets = Mathf.Abs (leftPosition.transform.position.x 
			- rightPosition.transform.position.x) * 2;
		float center = (leftPosition.transform.position.x 
					+ rightPosition.transform.position.x) / 2;
		transform.position = new Vector3 (center, transform.position.y,
			distanceTotargets > maxDistance ? -distanceTotargets : -maxDistance);
	}
}
