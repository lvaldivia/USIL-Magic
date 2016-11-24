Bullet = function(state, x, y) {
  Phaser.Sprite.call(this, state.game, x, y, 'bullet');
  this.state = state;
  this.game = state.game;
  this.game.physics.arcade.enable(this);
  this.body.velocity.x = 100;
};

Bullet.prototype = Object.create(Phaser.Sprite.prototype);
Bullet.prototype.constructor = Bullet;

Bullet.prototype.update = function(){
  if(this.x >= this.game.width) {
    this.kill();
  }
};