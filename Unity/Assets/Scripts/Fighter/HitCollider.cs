using UnityEngine;
using System.Collections;

public class HitCollider : MonoBehaviour {

	public string name;
	public Fighter owner;
	public int damage;

	void OnTriggerEnter(Collider coll){
		if (owner.attacking) {
			Fighter other = coll.gameObject.GetComponent<Fighter> ();
			if (other != null) {
				other.giveDamage (damage);
			}	
		}

	
	}
}
