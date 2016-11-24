using UnityEngine;
using System.Collections;

public class FighterStateBehavior : StateMachineBehaviour {

	public float horizontalForce;
	public float verticalForce;
	public AudioClip clip;
	public PlayerStates state;

	protected Fighter fighter;
	 // OnStateEnter is called when a transition starts and the state machine starts to evaluate this state
	override public void OnStateEnter(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
		if (fighter == null) {
			fighter = animator.gameObject.GetComponent<Fighter> ();
		}
		fighter.currentState = state;
		fighter.body.AddRelativeForce(new Vector3(0,verticalForce,0));
		if (clip != null) {
			SoundManager.playSound (fighter.source, clip);
		}
	}

	// OnStateUpdate is called on each Update frame between OnStateEnter and OnStateExit callbacks
	override public void OnStateUpdate(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
		
		fighter.body.AddRelativeForce(new Vector3(0,0,horizontalForce));
	}

	// OnStateExit is called when a transition ends and the state machine finishes evaluating this state
	//override public void OnStateExit(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateMove is called right after Animator.OnAnimatorMove(). Code that processes and affects root motion should be implemented here
	//override public void OnStateMove(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateIK is called right after Animator.OnAnimatorIK(). Code that sets up animation IK (inverse kinematics) should be implemented here.
	//override public void OnStateIK(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}
}
